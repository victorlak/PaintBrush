/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vitorrural.lp_trabalho;

import java.awt.Graphics;

/**
 *
 * @author USUARIO
 */
public class Circulo extends D2 {

    public int raio;

    @Override public double area() {
        return Math.PI * raio * raio;
    }

    @Override public double perimetro() {
        return 2 * Math.PI * raio;
    }

    @Override
    public void desenhar(Graphics g) {
        g.setColor(cor);
        g.drawOval(x, y, raio*2, raio*2);
    }
}
