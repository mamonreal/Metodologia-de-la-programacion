package MP3;


import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MiguelÁngel
 */
public class Snake {
    private LinkedList <Punto> snake = new LinkedList();
    
    public Snake(Punto punto) {
        snake.addFirst(punto);
    }
    
    public void add(Punto punto) {
        snake.addFirst(punto);
    }
    
    public void remove() {
        snake.removeLast();
    }
    
    public Punto getHead() {
        return snake.getFirst();
    }
    
    public Punto getTail() {
        return snake.getLast();
    }
    
    public boolean contains(Punto punto) {
        return snake.contains(punto);
    }
}
