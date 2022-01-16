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
public class AutoStock extends Stock implements Serializable {

    private String price = "";
    private String pe = "";
    private String roe = "";
    private String beta = "";
    private String dividend = "";

    public AutoStock(){
        
    }
    
    public AutoStock(String stockname, boolean auto, String ticker, String exchange, String npm, String currentratio) {
        super(stockname, true, ticker, exchange, npm, currentratio);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPe() {
        return pe;
    }

    public String getRoe() {
        return roe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getBeta() {
        return beta;
    }

    public String getDividend() {
        return dividend;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public void setDividend(String dividend) {
        this.dividend = dividend;
    }
}
