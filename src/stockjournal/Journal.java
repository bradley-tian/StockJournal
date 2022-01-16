/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockjournal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author bradl
 */
//implements a linked list data structure
public class Journal implements Serializable {

    private EntryNode front;

    public Journal() {
        front = null;
    }

    public void add(LocalDate date, LocalTime time, String body) {
        //adding to the start of the collection
        if (front == null) {
            front = new EntryNode(date, time, body);
        } else {
            //adding to the end of the collection
            EntryNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new EntryNode(date, time, body);
        }
    }

    public void edit(String date, String time, String body) {
        //add to the specific location 
        EntryNode current = front;
        while (current.next != null) {
            if (current.date.toString().equals(date) && current.time.toString().equals(time)) {
                current.body = body;
            }
            current = current.next;
        }
    }

    public String getBody(String date, String time) {
        EntryNode current = front;
        while (current.next != null) {
            if (current.date.toString().equals(date) && current.time.toString().equals(time)) {
                return current.body;
            }
            current = current.next;
        }
        return "Error reading message.";
    }

    public String getDate(String date, String time) {
        EntryNode current = front;
        while (current.next != null) {
            if (current.date.toString().equals(date) && current.time.toString().equals(time)) {
                return current.date.toString();
            }
            current = current.next;
        }
        return "Error reading date.";
    }

    public void remove(String date, String time) {
        if (!(front == null)) {
            if (front.date.toString().equals(date) && front.time.toString().equals(time)) {
                front = front.next;
            } else {
                EntryNode current = front;
                while (current.next != null) {
                    if (current.next.date.toString().equals(date) && current.time.toString().equals(time)) {
                        current.next = current.next.next;
                    }
                }
            }
        }
    }

    public int size() {
        int count = 0;
        if (front == null) {
            return count;
        } else {
            EntryNode current = front;
            while (current.next != null) {
                count++;
                current = current.next;
            }
        }
        return count;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public ArrayList<String> iterateDates() {
        ArrayList<String> dates = new ArrayList<>();
        if (front == null) {
            return dates;
        } else {
            EntryNode current = front;
            while (current.next != null) {
                dates.add(current.date.toString());
                current = current.next;
            }
            return dates;
        }
    }
    
    public ArrayList<String> iterateTime(){
        ArrayList<String> time = new ArrayList<>();
        if (front == null) {
            return time;
        } else {
            EntryNode current = front;
            while (current.next != null) {
                time.add(current.time.toString());
                current = current.next;
            }
            return time;
        }
    }
}
