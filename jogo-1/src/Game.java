import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = 480;

    public int contador = 0;                                //Contador de pontos
    public int vida = 100;                                  //Vida do personagem

    public Game() {
        Dimension dimension= new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);
    }

    public void update(){                                      //Atualiza o jogo
        // vida --;
    }

    public void render(){                                     //Renderiza o jogo
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();     //Pega o objeto grafico do buffer
        g.setColor(Color.BLACK);      //Define a cor do fundo grafico como preta
        g.fillRect(0, 0, WIDTH, HEIGHT);  //Pinta o fundo do tamanho da tela


        ImageIcon icon = new ImageIcon("src/img/favorito (2).png");
        Image image = icon.getImage(); // Retrieve the Image from the ImageIcon

        if (vida == 100) {
            g.drawImage(image, 20, 20, this);
            g.drawImage(image, 60, 20, this);
            g.drawImage(image, 100, 20, this);
            g.drawImage(image, 140, 20, this);
        } else if (vida > 50) {
            g.drawImage(image, 20, 20, this);
            g.drawImage(image, 60, 20, this);
            g.drawImage(image, 100, 20, this);
        } else if (vida > 25) {
            g.drawImage(image, 20, 20, this);
            g.drawImage(image, 60, 20, this);
        } else if (vida == 25) {
            g.drawImage(image, 20, 20, this);
        } else  {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.drawString("Game Over", 300, 240);
        }


    

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Pontos: " + contador, 550, 35);

        bs.show();
        //teste 
    }

    public static void main(String[] args) {                  //Metodo principal
        Game game = new Game();    
        JFrame jframe = new JFrame("Meu jogo");   
        jframe.add(game);      
        jframe.setLocationRelativeTo(null);    
        jframe.pack();     
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        jframe.setVisible(true); 

        new Thread(game).start();
    }

    

    @Override
    public void run() {
        while(true){
            update();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
