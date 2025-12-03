/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vitorrural.lp_trabalho;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class PainelDesenho extends JPanel {

    private TipoFerramenta ferramenta = TipoFerramenta.CIRCULO;
    private Color corAtual = Color.BLACK;

    private int x0, y0;   // ponto inicial do clique
    private int x1, y1;   // ponto atual durante arraste

    private ArrayList<Ponto> figuras = new ArrayList<>();

    public PainelDesenho() {

        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x0 = e.getX();
                y0 = e.getY();

                if(ferramenta == TipoFerramenta.SPRAY){
                    aplicarSpray(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();

                switch (ferramenta) {

                    case CIRCULO:
                        adicionarCirculoFinal();
                        break;

                    case RETANGULO:
                        adicionarRetanguloFinal();
                        break;

                    case BORRACHA:
                        adicionarBorracha(e.getX(), e.getY());
                        break;
                }

                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();

                if(ferramenta == TipoFerramenta.SPRAY){
                    aplicarSpray(e.getX(), e.getY());
                }

                if(ferramenta == TipoFerramenta.BORRACHA){
                    adicionarBorracha(e.getX(), e.getY());
                }

                repaint();
            }
        });
    }

    //--------------------------------------------------------------------------

    public void setFerramenta(TipoFerramenta f){
        ferramenta = f;
    }

    public void setCor(Color c){
        corAtual = c;
    }

    //--------------------------------------------------------------------------

    private void adicionarCirculoFinal(){
        int raio = (int) Math.hypot(x1 - x0, y1 - y0);

        Circulo c = new Circulo();
        c.x = x0 - raio;
        c.y = y0 - raio;
        c.raio = raio;
        c.cor = corAtual;

        figuras.add(c);
    }

    private void adicionarRetanguloFinal(){
        Retangulo r = new Retangulo();
        r.x = Math.min(x0, x1);
        r.y = Math.min(y0, y1);
        r.base = Math.abs(x1 - x0);
        r.largura = Math.abs(y1 - y0);
        r.cor = corAtual;
        r.corInterna = corAtual;

        figuras.add(r);
    }

    private void aplicarSpray(int x, int y){
        int raio = 20; 
        for(int i=0;i<40;i++){
            int rx = (int)(Math.random()*raio - raio/2);
            int ry = (int)(Math.random()*raio - raio/2);
            if(rx*rx + ry*ry <= raio*raio){
                Ponto p = new Ponto();
                p.x = x + rx;
                p.y = y + ry;
                p.cor = corAtual;
                figuras.add(p);
            }
        }
    }

    private void adicionarBorracha(int x, int y){
        int tamanho = 20;

        Retangulo r = new Retangulo();
        r.x = x - tamanho/2;
        r.y = y - tamanho/2;
        r.base = tamanho;
        r.largura = tamanho;
        r.corInterna = Color.WHITE;
        r.cor = Color.WHITE;

        figuras.add(r);
    }

    //--------------------------------------------------------------------------

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // desenha todas as figuras já criadas
        for(Ponto p : figuras){
            p.desenhar(g);
        }

        // preview durante arraste
        if(ferramenta == TipoFerramenta.CIRCULO){
            int raio = (int)Math.hypot(x1 - x0, y1 - y0);
            g.setColor(corAtual);
            g.drawOval(x0 - raio, y0 - raio, raio*2, raio*2);
        }
        else if(ferramenta == TipoFerramenta.RETANGULO){
            g.setColor(corAtual);
            g.drawRect(
                Math.min(x0, x1),
                Math.min(y0, y1),
                Math.abs(x1 - x0),
                Math.abs(y1 - y0)
            );
        }
    }
}
