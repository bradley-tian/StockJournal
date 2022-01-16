/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockjournal;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bradl
 */
public class JournalPage extends javax.swing.JFrame {

    private String stockname;
    private String ticker;
    private ArrayList<String> financials;
    private Stock stock;
    private AutoStock autostock;
    private boolean auto;

    public JournalPage() {
        initComponents();

    }

    public JournalPage(String stockname, String ticker) {
        this();
        this.stockname = stockname;
        this.ticker = ticker;

        String currentDirectory = System.getProperty("user.dir");
        currentDirectory.replace("'\'", "\\");
        File stockFile = new File(currentDirectory + "\\" + stockname + ".txt");

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(stockFile.getPath()));

            try {
                stock = (Stock) is.readObject();
                populateEntries(stock);
                is.close();

                //check if user decided to allow auto-generated information for this stock
                if (stock.isAuto()) {
                    auto = true;
                    autostock = (AutoStock) stock;
                    stockNameLabel.setText(autostock.getStockName());
                    exchangeTickerLabel.setText(autostock.getExchange() + ": " + autostock.getTicker());
                    npmField.setText(autostock.getNpm());
                    currentRatioField.setText(autostock.getCurrentratio());
                    financials = getLiveFinancials(ticker);

                } else {
                    auto = false;
                    stockNameLabel.setText(stock.getStockName());
                    exchangeTickerLabel.setText(stock.getExchange() + ": " + stock.getTicker());
                    npmField.setText(stock.getNpm());
                    currentRatioField.setText(stock.getCurrentratio());
                    currentSharePriceLabel.setText("Unavailable");
                    peField.setText("");
                    roeField.setText("");
                    betaField.setText("");
                    dividendField.setText("");
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("class error");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<String> getLiveFinancials(String ticker) {
        ArrayList<String> results = new ArrayList<>();
        try {
            //establish connection with CNBC web API
            URL url = new URL("https://quote.cnbc.com/quote-html-webservice/restQuote/symbolType/symbol?symbols=" + ticker + "&output=json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("accept", "*/*");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //Deserializing with help from gson library
            String result = (content.toString());
            Gson gson = new Gson();
            Response deserialized = gson.fromJson(result, Response.class);

            results.add(deserialized.getFormattedQuoteResult().getFormattedQuote().get(0).getLast());
            results.add(deserialized.getFormattedQuoteResult().getFormattedQuote().get(0).getPe());
            results.add(deserialized.getFormattedQuoteResult().getFormattedQuote().get(0).getROETTM());
            results.add(deserialized.getFormattedQuoteResult().getFormattedQuote().get(0).getBeta());
            results.add(deserialized.getFormattedQuoteResult().getFormattedQuote().get(0).getDividendyield());

            currentSharePriceLabel.setText(results.get(0));
            peField.setText(results.get(1));
            roeField.setText(results.get(2));
            betaField.setText(results.get(3));
            dividendField.setText(results.get(4));

        } catch (MalformedURLException e) {
            BadConnectionDialog dialog = new BadConnectionDialog(this, true);
            dialog.setLocation(700, 400);
            dialog.setVisible(true);
            currentSharePriceLabel.setText(autostock.getPrice());
            peField.setText(autostock.getPe());
            roeField.setText(autostock.getRoe());
            betaField.setText(autostock.getBeta());
            dividendField.setText(autostock.getDividend());
        } catch (IOException e) {
            BadConnectionDialog dialog = new BadConnectionDialog(this, true);
            dialog.setLocation(700, 400);
            dialog.setVisible(true);
            currentSharePriceLabel.setText(autostock.getPrice());
            peField.setText(autostock.getPe());
            roeField.setText(autostock.getRoe());
            betaField.setText(autostock.getBeta());
            dividendField.setText(autostock.getDividend());
        }

        return results;
    }

    public static double getValue(Pattern pattern, String query) {
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            double result = Double.parseDouble(matcher.group(1));
            return result;
        }
        return -1;
    }

    public void populateEntries(Stock stock) {
        entriesArea.setLineWrap(true);
        entriesArea.setWrapStyleWord(true);
        Journal journal = stock.getJournal();
        if (journal.isEmpty()) {
            entriesArea.setText("No Entries.");
        } else {
            ArrayList<String> dates = journal.iterateDates();
            ArrayList<String> time = journal.iterateTime();
            String display = "";
            String line = "____________________________";
            for (int i = 0; i < time.size(); i++) {
                display += dates.get(i) + "\n";
                display += line;
                display += "\n";
                display += journal.getBody(dates.get(i), time.get(i));
                display += "\n\n";
            }
            entriesArea.setText(display);
        }
    }

    public void addEntry(String date, String body) {
        entriesArea.setLineWrap(true);
        entriesArea.setWrapStyleWord(true);

        String display = "";
        String line = "____________________________";
        display += date + "\n";
        display += line;
        display += "\n";
        display += body;
        display += "\n\n";
        entriesArea.setText(entriesArea.getText() + display);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stockNameLabel = new javax.swing.JLabel();
        exchangeTickerLabel = new javax.swing.JLabel();
        currentsharepricetext = new javax.swing.JLabel();
        currentSharePriceLabel = new javax.swing.JLabel();
        financialmetrics = new javax.swing.JLabel();
        financialmetrics1 = new javax.swing.JLabel();
        financialmetrics2 = new javax.swing.JLabel();
        financialmetrics3 = new javax.swing.JLabel();
        financialmetrics4 = new javax.swing.JLabel();
        financialsReturnButton = new javax.swing.JButton();
        npmField = new javax.swing.JTextField();
        currentRatioField = new javax.swing.JTextField();
        peField = new javax.swing.JTextField();
        roeField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        financialmetrics5 = new javax.swing.JLabel();
        financialmetrics6 = new javax.swing.JLabel();
        betaField = new javax.swing.JTextField();
        dividendField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        entriesArea = new javax.swing.JTextArea();
        saveButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        stockNameLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        stockNameLabel.setText("[Stock Name]");

        exchangeTickerLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exchangeTickerLabel.setText("[Exchange]: [Ticker]");

        currentsharepricetext.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        currentsharepricetext.setText("Current Share Price:");

        currentSharePriceLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        currentSharePriceLabel.setText("0.00");

        financialmetrics.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        financialmetrics.setText("Financial Metrics:");

        financialmetrics1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        financialmetrics1.setText("Net Profit Margin (NPM):");

        financialmetrics2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        financialmetrics2.setText("Current Ratio: ");

        financialmetrics3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        financialmetrics3.setText("Price to Earnings (P/E) Ratio:");

        financialmetrics4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        financialmetrics4.setText("Return on Equity (ROE):");

        financialsReturnButton.setBackground(new java.awt.Color(100, 100, 100));
        financialsReturnButton.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        financialsReturnButton.setForeground(new java.awt.Color(255, 255, 255));
        financialsReturnButton.setText("Return to Home Page");
        financialsReturnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                financialsReturnButtonMouseClicked(evt);
            }
        });
        financialsReturnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financialsReturnButtonActionPerformed(evt);
            }
        });

        npmField.setBackground(new java.awt.Color(240, 240, 240));
        npmField.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        npmField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                npmFieldActionPerformed(evt);
            }
        });

        currentRatioField.setBackground(new java.awt.Color(240, 240, 240));
        currentRatioField.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        currentRatioField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentRatioFieldActionPerformed(evt);
            }
        });

        peField.setEditable(false);
        peField.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        peField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peFieldActionPerformed(evt);
            }
        });

        roeField.setEditable(false);
        roeField.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        roeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roeFieldActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("If adding / making changes to existing journal entries and financial \ninformation, please save before returning to home page. ");
        jScrollPane2.setViewportView(jTextArea1);

        saveButton.setBackground(new java.awt.Color(100, 100, 100));
        saveButton.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        financialmetrics5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        financialmetrics5.setText("Beta:");

        financialmetrics6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        financialmetrics6.setText("Dividend Yield:");

        betaField.setEditable(false);
        betaField.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        betaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                betaFieldActionPerformed(evt);
            }
        });

        dividendField.setEditable(false);
        dividendField.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        dividendField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dividendFieldActionPerformed(evt);
            }
        });

        entriesArea.setEditable(false);
        entriesArea.setColumns(20);
        entriesArea.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        entriesArea.setRows(5);
        entriesArea.setText("Journal begins here.");
        entriesArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(entriesArea);

        saveButton1.setBackground(new java.awt.Color(100, 100, 100));
        saveButton1.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        saveButton1.setForeground(new java.awt.Color(255, 255, 255));
        saveButton1.setText("Edit Entries");
        saveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButton1MouseClicked(evt);
            }
        });
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(exchangeTickerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(212, 212, 212)
                                .addComponent(currentsharepricetext, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(stockNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentSharePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(saveButton1)
                                .addGap(27, 27, 27)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(financialsReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(financialmetrics, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(financialmetrics2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(currentRatioField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(financialmetrics1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(npmField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(financialmetrics3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(financialmetrics4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(5, 5, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(roeField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(peField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(financialmetrics5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(financialmetrics6, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dividendField, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                            .addComponent(betaField))))
                                .addGap(152, 152, 152)))))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(stockNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeTickerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentsharepricetext, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentSharePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(financialmetrics, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(npmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financialmetrics1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financialmetrics2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentRatioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financialmetrics3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(peField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financialmetrics4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financialmetrics5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(betaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financialmetrics6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dividendField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financialsReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void financialsReturnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financialsReturnButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financialsReturnButtonActionPerformed

    private void npmFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_npmFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_npmFieldActionPerformed

    private void currentRatioFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentRatioFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentRatioFieldActionPerformed

    private void peFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_peFieldActionPerformed

    private void roeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roeFieldActionPerformed

    private void financialsReturnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_financialsReturnButtonMouseClicked
        setVisible(false);
        dispose();
    }//GEN-LAST:event_financialsReturnButtonMouseClicked

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        String currentDirectory = System.getProperty("user.dir");
        currentDirectory.replace("'\'", "\\");
        File stockFile = new File(currentDirectory + "\\" + stockname + ".txt");
        String temp = "temp.txt";
        File newFile = new File(currentDirectory + "\\" + temp);

        if (auto) {
            autostock.setNpm(npmField.getText());
            autostock.setCurrentratio(currentRatioField.getText());
            autostock.setPrice(currentSharePriceLabel.getText());
            autostock.setPe(peField.getText());
            autostock.setRoe(roeField.getText());
            autostock.setBeta(betaField.getText());
            autostock.setDividend(dividendField.getText());
            autostock.getJournal().add(LocalDate.now(), LocalTime.now(), entriesArea.getText());

            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(newFile.getPath()));
                os.writeObject(autostock);
                os.close();
                stockFile.delete();
                File dump = new File(currentDirectory + "\\" + stockname + ".txt");
                newFile.renameTo(dump); //sequence to write data into new file and replace the old one

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            stock.setNpm(npmField.getText());
            stock.setCurrentratio(currentRatioField.getText());
            stock.getJournal().add(LocalDate.now(), LocalTime.now(), entriesArea.getText());

            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(newFile.getPath()));
                os.writeObject(stock);
                os.close();
                stockFile.delete();
                File dump = new File(currentDirectory + "\\" + stockname + ".txt");
                newFile.renameTo(dump); //sequence to write data into new file and replace the old one

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SaveDialog dialog = new SaveDialog(this, true);
        dialog.setLocation(700, 400);
        dialog.setVisible(true);
        setVisible(false);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonMouseClicked

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void betaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_betaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_betaFieldActionPerformed

    private void dividendFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dividendFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dividendFieldActionPerformed

    private void saveButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButton1MouseClicked
        new EditEntriesPage(stockname, this).setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton1MouseClicked

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JournalPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JournalPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JournalPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JournalPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JournalPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField betaField;
    private javax.swing.JTextField currentRatioField;
    private javax.swing.JLabel currentSharePriceLabel;
    private javax.swing.JLabel currentsharepricetext;
    private javax.swing.JTextField dividendField;
    private javax.swing.JTextArea entriesArea;
    private javax.swing.JLabel exchangeTickerLabel;
    private javax.swing.JLabel financialmetrics;
    private javax.swing.JLabel financialmetrics1;
    private javax.swing.JLabel financialmetrics2;
    private javax.swing.JLabel financialmetrics3;
    private javax.swing.JLabel financialmetrics4;
    private javax.swing.JLabel financialmetrics5;
    private javax.swing.JLabel financialmetrics6;
    private javax.swing.JButton financialsReturnButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField npmField;
    private javax.swing.JTextField peField;
    private javax.swing.JTextField roeField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveButton1;
    private javax.swing.JLabel stockNameLabel;
    // End of variables declaration//GEN-END:variables
}
