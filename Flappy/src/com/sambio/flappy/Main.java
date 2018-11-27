package com.sambio.flappy;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.opengl.GL;

import com.sambio.flappy.input.Input;
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
		if (!glfwInit()) {
			//TODO::
			System.out.println("ops");
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, "Flappy", NULL, NULL);
		

		glfwSetWindowPos(window, 100, 100);
		
		glfwSetKeyCallback(window, new Input());
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		GL.createCapabilities();
		
		glClearColor(255, 255, 255, 255);
		glEnable(GL_DEPTH_TEST);
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		
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
		if (Input.keys[GLFW_KEY_SPACE]) {
			System.out.println("FLAT");
		}
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(window);
		
	}
	
	public static void main(String[] args) {
		new Main().Start();
	}

}
