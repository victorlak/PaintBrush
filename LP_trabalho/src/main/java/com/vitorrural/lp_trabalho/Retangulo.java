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
public class Retangulo extends D2 {

    public int base, largura;

    @Override
    public double area() {
        return base * largura;
    }

    @Override
    public double perimetro() {
        return 2 * (base + largura);
    }

    @Override
    public void desenhar(Graphics g) {
        g.setColor(corInterna);
        g.fillRect(x, y, base, largura);

        g.setColor(cor);
        g.drawRect(x, y, base, largura);
    }
}
