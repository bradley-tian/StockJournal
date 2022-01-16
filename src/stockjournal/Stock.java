/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockjournal;

import java.io.Serializable;

/**
 *
 * @author bradl
 */
public class Stock implements Serializable {
    private String stockname = "";
    private boolean auto = false;
    private String ticker = "";
    private String exchange = "";
    private String npm = "";
    private String currentratio = "";
    private Journal journal;

    public Stock(){
        journal = new Journal();
    }
    
    public Stock(String stockname, boolean auto, String ticker, String exchange, String npm, String currentratio) {
        this();
        this.stockname = stockname;
        this.auto = auto;
        this.ticker = ticker;
        this.exchange = exchange;
        this.npm = npm;
        this.currentratio = currentratio;
    }
    
    public String getStockName() {
        return stockname;
    }

    public String getTicker() {
        return ticker;
    }

    public String getExchange() {
        return exchange;
    }

    public String getNpm() {
        return npm;
    }

    public String getCurrentratio() {
        return currentratio;
    }

    public void setStockName(String stockname) {
        this.stockname = stockname;
    }
    
    public boolean isAuto (){
        return auto;
    }
    
    public void setAuto(boolean auto){
        this.auto = auto;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public void setCurrentratio(String currentratio) {
        this.currentratio = currentratio;
    }
    
    public Journal getJournal(){
        return journal;
    }
    
    public void setJournal(Journal journal){
        this.journal = journal;
    }
}
