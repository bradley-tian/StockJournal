/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockjournal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author bradl
 */
public class EntryNode implements Serializable {

    LocalDate date;
    LocalTime time;
    String body;
    EntryNode next;

    public EntryNode(LocalDate date, LocalTime time, String body) {
        this.body = body;
        this.date = date;
        this.time = time;
        this.next = null;
    }

    public EntryNode(LocalDate date, LocalTime time, String body, EntryNode next) {
        this(date, time, body);
        this.next = next;
    }

}
