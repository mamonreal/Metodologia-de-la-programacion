/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MP3;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import javax.swing.JPanel;

/**
 *
 * @author MiguelÁngel
 */
public class Modelo extends Observable{
    private Snake snake;
    //Este punto indica la localización de la comida
    private Punto food;
    //Matriz de referencia
        //
    private final int ROWS;
    private final int COLUMNS;
    private JPanel[][] board;
    //Puntos conseguidos
    private int points;
    ActualizaPosicion actualizaPosicion = new ActualizaPosicion();
    
    public Modelo() {
        ROWS = 40;
        COLUMNS = 60;
        points = 0;
        board = new JPanel [ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++) {
                JPanel p = new JPanel();
                p.setName(j + ", " + i);
                p.setBackground(Color.white);
                board[i][j] = p;
            }
        Punto head = new Punto(15, 15);
        snake = new Snake(head);
        board[head.getY()][head.getX()].setBackground(Color.red);
        Food();
        addKeyListener(actualizaPosicion);
        actualizaPosicion.start();
    }

    //GETTERS & SETTERS
    public Punto getFood() {
        return food;
    }

    public void setFood(Punto food) {
        this.food = food;
    }
    
    public Punto getHead() {
        return snake.getHead();
    }
    
    public Punto getTail() {
        return snake.getTail();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }
    
    //CREAR COMIDA EN LOCALIZACION ALEATORIA
    public void Food() {
        int x = (int)(Math.random()*ROWS);
        int y = (int)(Math.random()*COLUMNS);
        food = new Punto(x, y);
        if (! snake.contains(food))
            notifyChanges("Food");
        else
            Food();
    }
    
    //MOVIMIENTOS
    public void moveRight() {
        Punto aux = snake.getHead();
        Punto head = new Punto(aux.getX() + 1, aux.getY());
        board[head.getY()][head.getX()].setBackground(Color.red);
        snake.add(head);
        Punto tail = snake.getTail();
        board[tail.getY()][tail.getX()].setBackground(Color.white);
        snake.remove();
        notifyChanges("Move");
    }
    
    public void moveLeft() {
        Punto aux = snake.getHead();
        Punto head = new Punto(aux.getX() - 1, aux.getY());
        board[head.getY()][head.getX()].setBackground(Color.red);
        snake.add(head);
        Punto tail = snake.getTail();
        board[tail.getY()][tail.getX()].setBackground(Color.white);
        snake.remove();
        notifyChanges("Move");
    }
    
    public void moveUp() {
        Punto aux = snake.getHead();
        Punto head = new Punto(aux.getX(), aux.getY() - 1);
        board[head.getY()][head.getX()].setBackground(Color.red);
        snake.add(head);
        Punto tail = snake.getTail();
        board[tail.getY()][tail.getX()].setBackground(Color.white);
        snake.remove();
        notifyChanges("Move");
    }
    
    public void moveDown() {
        Punto aux = snake.getHead();
        Punto head = new Punto(aux.getX(), aux.getY() + 1);
        board[head.getY()][head.getX()].setBackground(Color.red);
        snake.add(head);
        Punto tail = snake.getTail();
        board[tail.getY()][tail.getX()].setBackground(Color.white);
        snake.remove();
        notifyChanges("Move");
    }
    
    //Notificar camibios a los observadores
    public void notifyChanges(String msg) {
        this.setChanged();
        this.notifyObservers(msg);
    }
    
    class ActualizaPosicion extends Thread implements KeyListener{
        private int vel = 10;
        private int direction = 39;
        private boolean change = true;
        private boolean fin = true;
        private boolean pause = true;
        
        @Override
        public void run() {
            while(fin) {
                if(! pause) {
                    try {
                    Thread.sleep(vel*7);
                    }
                    catch (InterruptedException ex) {
                    System.err.println("Error en ActualizaTablero"); 
                    }
                    move(direction);
                    change = true;
                }
            }
        }
        
        public void move(int d) {
            switch(d) {
                case 39:
                    moveRight();
                    break;
                case 37:
                    moveLeft();
                    break;
                case 38:
                    moveUp();
                    break;
                case 40:
                    moveDown();
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if ((e.getKeyCode() < 41) && (e.getKeyCode() > 36) && change) {
                if ((direction == 38)&&(e.getKeyCode() != 40)) {
                    direction = e.getKeyCode();
                    change = false;
                    System.out.println(e.getKeyCode());
                }                        
                if ((direction == 40)&&(e.getKeyCode() != 38)) {
                        direction = e.getKeyCode();
                        change = false;
                        System.out.println(e.getKeyCode());
                }
                if ((direction == 37)&&(e.getKeyCode() != 39)) {
                    direction = e.getKeyCode();
                    change = false;
                    System.out.println(e.getKeyCode());
                }
                if ((direction == 39)&&(e.getKeyCode() != 37)) {
                        direction = e.getKeyCode();
                        change = false;
                        System.out.println(e.getKeyCode());
                }
            }
            if (e.getKeyCode() == 32) {
                pause = !pause;
                System.out.println(e.getKeyCode());
            }
            //Si se le da al espacio el juego se para, pero no se vuelve a poner en marcha, hay que buscar solucion
            /*
            if (e.getKeyCode() == 32)
                pause = !pause;
            if ((e.getKeyCode() == 521) || (e.getKeyCode() == 107))
                masRapido();
            if ((e.getKeyCode() == 45) || (e.getKeyCode() == 109))
                masLento();
                    */
        }

        @Override
        public void keyPressed(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
