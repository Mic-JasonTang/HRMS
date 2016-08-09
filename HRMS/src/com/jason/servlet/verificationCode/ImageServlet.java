package com.jason.servlet.verificationCode;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ImageServlet extends HttpServlet implements Serializable {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//创建一个72*40的RGB图片
		BufferedImage bi = new BufferedImage(72, 40, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		Color c = new Color(80, 170, 170); //背景色
		g.setColor(c);
		g.fillRect(0, 0, 72, 40); //72*40的图片
		
		char[] ch = "ABCDEFGHIJKLMNOPQRST0123456789".toCharArray();
		Random r = new Random();
		int len = ch.length, index;
		final int CODE_MAX_LENGTH = 4;
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<CODE_MAX_LENGTH; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(100), r.nextInt(150), r.nextInt(200)));
			g.setFont(new Font("宋体", Font.BOLD, 18));
			g.drawString(ch[index]+"", (i*15) + 6, (r.nextInt(10) + 20));
			buffer.append(ch[index]); 
		}
		session.setAttribute("picCode", buffer.toString());
		System.out.println("服务器:生成验证码成功:" +  session.getAttribute("picCode"));
		ImageIO.write(bi, "JPG", response.getOutputStream());
	}
}
