package cc.dwr.test;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

@SuppressWarnings("deprecation")
public class DwrPush {

	/**
	 * 实现客户端发送过来的信息推送出去
	 * 
	 * @param msg 客户端发送过来的消息
	 */
	public void send(String msg) {
		System.out.println(msg);
		WebContext webContext = WebContextFactory.get();
		Collection<ScriptSession> sessions = webContext.getAllScriptSessions();
		/**
		 * 推送消息所要执行的业务逻辑
		 */
		// 构建发送所需的JS脚本
		ScriptBuffer scriptBuffer = new ScriptBuffer();
		// 调用客户端的js脚本函数
		scriptBuffer.appendScript("revice(");
		scriptBuffer.appendData(msg);
		scriptBuffer.appendScript(")");
		
		// 实现推送
		// 为所有的用户服务
		Util util = new Util(sessions);
		util.addScript(scriptBuffer);
	}
	
	/**
	 * 实现客户端发送过来的信息推送出去
	 * 
	 * @param msg 客户端发送过来的消息
	 */
	public void send2(final String msg) {
		System.out.println(msg);
		Browser.withAllSessions(new Runnable() {
			@Override
			public void run() {
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				
				/**
				 * 推送消息所要执行的业务逻辑
				 */
				// 构建发送所需的JS脚本
				ScriptBuffer scriptBuffer = new ScriptBuffer();
				// 调用客户端的js脚本函数
				scriptBuffer.appendCall("revice", msg);
				
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(scriptBuffer);
				}
			}
		});
	}
	
	/**
	 * 实现客户端发送过来的信息推送出去
	 * 实现精准推送
	 * 
	 * @param msg 客户端发送过来的消息
	 */
	public void send3(final String msg) {
		System.out.println(msg);
//		ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
//		scriptSession.setAttribute(name, value);
		
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			@Override
			public boolean match(ScriptSession session) {
				// 执行过滤操作，true允许，false不允许
				return false;
			}
		}, new Runnable() {
			// 执行推送消息
			Collection<ScriptSession> sessions = Browser.getTargetSessions();
			
			@Override
			public void run() {
				/**
				 * 推送消息所要执行的业务逻辑
				 */
				// 构建发送所需的JS脚本
				ScriptBuffer scriptBuffer = new ScriptBuffer();
				// 调用客户端的js脚本函数
				scriptBuffer.appendCall("revice", msg);
				
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(scriptBuffer);
				}
			}
		});
	}
	
	
}
