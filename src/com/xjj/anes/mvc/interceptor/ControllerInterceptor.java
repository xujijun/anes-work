package com.xjj.anes.mvc.interceptor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjj.anes.annotation.SessionChecking;
import com.xjj.anes.bean.common.LoginUser;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.cache.CacheConstants;
import com.xjj.anes.cache.CacheManager;
import com.xjj.anes.constants.MessageCode;
import com.xjj.anes.service.common.CacheSessionService;
import com.xjj.anes.service.common.SessionService;
import com.xjj.anes.utils.WebUtil;

public class ControllerInterceptor implements HandlerInterceptor {
	private Log log = LogFactory.getLog(this.getClass().getName());
	private Set<String> notCheckURISet = new HashSet<String>();
	private final String REQTIME = "_REQTIME_";
	private final String LOGINUSER = "_LOGINUSER_";
	private static Map<String, String> uriPermissionIdMap = null;

	//private static SessionService sessionService = CoreApplicationContext.getApplicationContext().getBean(SessionService.class);
	//private static CacheSessionService cacheSessionService = CoreApplicationContext.getApplicationContext().getBean(CacheSessionService.class);
	
	@Resource
	private SessionService sessionService;
	@Resource
	private CacheSessionService cacheSessionService;
	
	public void setNotCheckURISet(Set<String> notCheckURISet) {
		this.notCheckURISet = notCheckURISet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 允许跨域访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String requestUri = request.getRequestURI();
		if (!needCheckUri(request, requestUri)) {
			return true;
		}
		
		if(handler instanceof HandlerMethod){
			handler=((HandlerMethod)handler).getBean();
		}
		request.setAttribute(REQTIME, System.currentTimeMillis());
		
		LoginUser loginUser = handleSessionChecking(request, response, handler);
		request.setAttribute(LOGINUSER, loginUser);
		
		if (loginUser == null) {
			log.info(String.format("%-16s%-6s%-10s%s", request.getRemoteAddr(), "begin", "", requestUri));
			return false;
		}else{
			log.info(String.format("%-16s%-25s%-6s%-10s%s", request.getRemoteAddr(), loginUser.getCode() + "[" + loginUser.getSessionId() + "]", "begin", "", requestUri));
		}
		
		//检查该用户是否有对该uri访问的权限
		if (uriPermissionIdMap == null) {
			uriPermissionIdMap = (Map<String, String>) CacheManager.getInstance().get(CacheConstants.UriPermissionIdMap);
		}
		if (!handlePermissionChecking(request, response, loginUser)) {
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		String requestUri = request.getRequestURI();

		if (!needCheckUri(request, requestUri)) {
			return;
		}

		//获取登录用户
		Object obj = request.getAttribute(LOGINUSER);
		LoginUser loginUser = null;
		if (obj != null) {
			loginUser = (LoginUser) obj;
		}
		
		//log 请求结束事件
		long time = System.currentTimeMillis() - (Long) request.getAttribute(REQTIME); //请求所耗时长
		if (loginUser != null && loginUser.getId() != null && !"".equals(loginUser.getId())) {
			log.info(String.format("%-16s%-25s%-6s%-10s%s",	request.getRemoteAddr(), loginUser.getCode() + "[" + loginUser.getSessionId() + "]", "end", time + "(ms)", requestUri));
		} else {
			log.info(String.format("%-16s%-6s%-10s%s", request.getRemoteAddr(),	"end", time + "(ms)", requestUri));
		}
	}

	private boolean needCheckUri(HttpServletRequest request, String requestUri) {
		for (String key : notCheckURISet) {
			key = request.getContextPath() + key;
			if (requestUri.startsWith(key)) {
				return false;
			}
		}
		return true;
	}
	
	private LoginUser handleSessionChecking(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		LoginUser loginUser = new LoginUser();

		if (!needCheckUri(request, requestUri))	{
			return loginUser;
		}

		//System.out.println("handler: " + handler.getClass().getName());
		
		SessionChecking sessionChecking = AnnotationUtils.findAnnotation(handler.getClass(), SessionChecking.class);
		if (sessionChecking != null && sessionChecking.isCheck()) {// 需要检查Session
			loginUser = cacheSessionService.getLoginUser(sessionService.getSessionId(request));
			if (loginUser == null) {
				log.warn(String.format("%-18s%-50s%s", request.getRemoteAddr(), requestUri, "It is a no session request..."));
				if (WebUtil.isAjAxRequest(request))	{
					response.setHeader("AJAX_REQUEST_CHECKING", MessageCode.System.AJAX_REQUEST_CHECKING_NOSESSION);
					ResultBean rb = new ResultBean();
					rb.setSuccess(false);
					rb.setMessageCode(MessageCode.System.AJAX_REQUEST_CHECKING_NOSESSION);
					rb.setMessage("用户未登录或会话超时");
					responseResultBean(request, response, rb);
				} else {
					response.sendRedirect(request.getContextPath());
				}
			}
		}
		return loginUser;
	}
	
	//检查该请求的uri对应的permissionId是否在该用户的permission列表里面
	private boolean handlePermissionChecking(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser) throws Exception {
		if (loginUser != null && loginUser.getId() != null)	{
			String uri = request.getRequestURI();
			uri = uri.replaceFirst(request.getContextPath(), "");
			String requestedPermissionId = uriPermissionIdMap.get(uri);
			if (!StringUtils.isEmpty(requestedPermissionId)) {
				if (loginUser.getUser().getPermissionIdSet().contains(requestedPermissionId)) { //如果用户的Permission列表中有这个Permission
					return true;
					
				} else {
					log.warn(String.format("%-18s%-50s%s", request.getRemoteAddr(), request.getRequestURI(), "It is a no permission request..."));
					if (WebUtil.isAjAxRequest(request)) {
						response.setHeader("AJAX_REQUEST_CHECKING", MessageCode.System.AJAX_REQUEST_CHECKING_NOPERMISSION);
						ResultBean rb = new ResultBean();
						rb.setSuccess(false);
						rb.setMessageCode(MessageCode.System.AJAX_REQUEST_CHECKING_NOPERMISSION);
						rb.setMessage("用户无此功能操作权限");
						responseResultBean(request, response, rb);
					} else {
						response.sendRedirect(request.getContextPath());
					}
					return false;
				}
			}
		}
		return true;
	}
	
	private void responseResultBean(HttpServletRequest request, HttpServletResponse response, ResultBean rb) {
		try {
			//response.getOutputStream().write(new Gson().toJson(rb).getBytes("UTF-8"));
			response.getOutputStream().write(new ObjectMapper().writeValueAsString(rb).getBytes("UTF-8"));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
