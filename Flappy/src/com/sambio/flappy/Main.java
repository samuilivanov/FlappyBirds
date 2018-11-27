package com.sambio.flappy;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;

public class Main implements Runnable {
	
	private int width = 1280;
	private int height = 720;
	private String title = "Flappy";
	
	private boolean running = false;
	private Thread thread;
	
	private long window;
	
	public void Start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	private void init() {
		if (!GLFW.glfwInit()) {
			System.out.println("ops");
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, "Flappy", NULL, NULL);
		

		glfwSetWindowPos(window, 100, 100);
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		
		
	}
	
	public void run() {
		
		init();
		
		while (running) {
			update();
			render();
			if (glfwWindowShouldClose(window) == true) {
				running = false;
			}
		}
		

		
	}
	
	private void update() {
		glfwPollEvents();
	}
	
	private void render() {
		glfwSwapBuffers(window);
		
	}
	
	public static void main(String[] args) {
		new Main().Start();

	}

}
