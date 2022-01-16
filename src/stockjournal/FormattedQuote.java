/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockjournal;

/**
 *
 * @author bradl
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormattedQuote {

@SerializedName("symbol")
@Expose
private String symbol;
@SerializedName("symbolType")
@Expose
private String symbolType;
@SerializedName("code")
@Expose
private int code;
@SerializedName("name")
@Expose
private String name;
@SerializedName("shortName")
@Expose
private String shortName;
@SerializedName("onAirName")
@Expose
private String onAirName;
@SerializedName("altName")
@Expose
private String altName;
@SerializedName("last")
@Expose
private String last;
@SerializedName("last_timedate")
@Expose
private String lastTimedate;
@SerializedName("last_time")
@Expose
private String lastTime;
@SerializedName("changetype")
@Expose
private String changetype;
@SerializedName("type")
@Expose
private String type;
@SerializedName("subType")
@Expose
private String subType;
@SerializedName("exchange")
@Expose
private String exchange;
@SerializedName("source")
@Expose
private String source;
@SerializedName("open")
@Expose
private String open;
@SerializedName("high")
@Expose
private String high;
@SerializedName("low")
@Expose
private String low;
@SerializedName("change")
@Expose
private String change;
@SerializedName("change_pct")
@Expose
private String changePct;
@SerializedName("currencyCode")
@Expose
private String currencyCode;
@SerializedName("volume")
@Expose
private String volume;
@SerializedName("volume_alt")
@Expose
private String volumeAlt;
@SerializedName("provider")
@Expose
private String provider;
@SerializedName("previous_day_closing")
@Expose
private String previousDayClosing;
@SerializedName("altSymbol")
@Expose
private String altSymbol;
@SerializedName("realTime")
@Expose
private String realTime;
@SerializedName("curmktstatus")
@Expose
private String curmktstatus;
@SerializedName("pe")
@Expose
private String pe;
@SerializedName("mktcapView")
@Expose
private String mktcapView;
@SerializedName("dividend")
@Expose
private String dividend;
@SerializedName("dividendyield")
@Expose
private String dividendyield;
@SerializedName("beta")
@Expose
private String beta;
@SerializedName("tendayavgvol")
@Expose
private String tendayavgvol;
@SerializedName("pcttendayvol")
@Expose
private String pcttendayvol;
@SerializedName("yrhiprice")
@Expose
private String yrhiprice;
@SerializedName("yrhidate")
@Expose
private String yrhidate;
@SerializedName("yrloprice")
@Expose
private String yrloprice;
@SerializedName("yrlodate")
@Expose
private String yrlodate;
@SerializedName("eps")
@Expose
private String eps;
@SerializedName("sharesout")
@Expose
private String sharesout;
@SerializedName("revenuettm")
@Expose
private String revenuettm;
@SerializedName("ROETTM")
@Expose
private String rOETTM;
@SerializedName("NETPROFTTM")
@Expose
private String nETPROFTTM;
@SerializedName("GROSMGNTTM")
@Expose
private String gROSMGNTTM;
@SerializedName("TTMEBITD")
@Expose
private String tTMEBITD;
@SerializedName("DEBTEQTYQ")
@Expose
private String dEBTEQTYQ;
@SerializedName("fpe")
@Expose
private String fpe;
@SerializedName("feps")
@Expose
private String feps;
@SerializedName("streamable")
@Expose
private String streamable;
@SerializedName("issue_id")
@Expose
private String issueId;
@SerializedName("issuer_id")
@Expose
private String issuerId;
@SerializedName("countryCode")
@Expose
private String countryCode;
@SerializedName("timeZone")
@Expose
private String timeZone;

/**
* No args constructor for use in serialization
*
*/
public FormattedQuote() {
}

/**
*
* @param lastTime
* @param symbol
* @param yrlodate
* @param gROSMGNTTM
* @param curmktstatus
* @param rOETTM
* @param mktcapView
* @param source
* @param volumeAlt
* @param tTMEBITD
* @param type
* @param issuerId
* @param high
* @param streamable
* @param changetype
* @param lastTimedate
* @param sharesout
* @param yrhiprice
* @param altSymbol
* @param last
* @param eps
* @param timeZone
* @param nETPROFTTM
* @param changePct
* @param feps
* @param symbolType
* @param volume
* @param realTime
* @param name
* @param pcttendayvol
* @param subType
* @param exchange
* @param shortName
* @param tendayavgvol
* @param dEBTEQTYQ
* @param code
* @param previousDayClosing
* @param low
* @param provider
* @param countryCode
* @param dividend
* @param fpe
* @param altName
* @param beta
* @param issueId
* @param yrloprice
* @param change
* @param yrhidate
* @param onAirName
* @param dividendyield
* @param pe
* @param revenuettm
* @param currencyCode
* @param open
*/
public FormattedQuote(String symbol, String symbolType, int code, String name, String shortName, String onAirName, String altName, String last, String lastTimedate, String lastTime, String changetype, String type, String subType, String exchange, String source, String open, String high, String low, String change, String changePct, String currencyCode, String volume, String volumeAlt, String provider, String previousDayClosing, String altSymbol, String realTime, String curmktstatus, String pe, String mktcapView, String dividend, String dividendyield, String beta, String tendayavgvol, String pcttendayvol, String yrhiprice, String yrhidate, String yrloprice, String yrlodate, String eps, String sharesout, String revenuettm, String rOETTM, String nETPROFTTM, String gROSMGNTTM, String tTMEBITD, String dEBTEQTYQ, String fpe, String feps, String streamable, String issueId, String issuerId, String countryCode, String timeZone) {
super();
this.symbol = symbol;
this.symbolType = symbolType;
this.code = code;
this.name = name;
this.shortName = shortName;
this.onAirName = onAirName;
this.altName = altName;
this.last = last;
this.lastTimedate = lastTimedate;
this.lastTime = lastTime;
this.changetype = changetype;
this.type = type;
this.subType = subType;
this.exchange = exchange;
this.source = source;
this.open = open;
this.high = high;
this.low = low;
this.change = change;
this.changePct = changePct;
this.currencyCode = currencyCode;
this.volume = volume;
this.volumeAlt = volumeAlt;
this.provider = provider;
this.previousDayClosing = previousDayClosing;
this.altSymbol = altSymbol;
this.realTime = realTime;
this.curmktstatus = curmktstatus;
this.pe = pe;
this.mktcapView = mktcapView;
this.dividend = dividend;
this.dividendyield = dividendyield;
this.beta = beta;
this.tendayavgvol = tendayavgvol;
this.pcttendayvol = pcttendayvol;
this.yrhiprice = yrhiprice;
this.yrhidate = yrhidate;
this.yrloprice = yrloprice;
this.yrlodate = yrlodate;
this.eps = eps;
this.sharesout = sharesout;
this.revenuettm = revenuettm;
this.rOETTM = rOETTM;
this.nETPROFTTM = nETPROFTTM;
this.gROSMGNTTM = gROSMGNTTM;
this.tTMEBITD = tTMEBITD;
this.dEBTEQTYQ = dEBTEQTYQ;
this.fpe = fpe;
this.feps = feps;
this.streamable = streamable;
this.issueId = issueId;
this.issuerId = issuerId;
this.countryCode = countryCode;
this.timeZone = timeZone;
}

public String getSymbol() {
return symbol;
}

public void setSymbol(String symbol) {
this.symbol = symbol;
}

public FormattedQuote withSymbol(String symbol) {
this.symbol = symbol;
return this;
}

public String getSymbolType() {
return symbolType;
}

public void setSymbolType(String symbolType) {
this.symbolType = symbolType;
}

public FormattedQuote withSymbolType(String symbolType) {
this.symbolType = symbolType;
return this;
}

public int getCode() {
return code;
}

public void setCode(int code) {
this.code = code;
}

public FormattedQuote withCode(int code) {
this.code = code;
return this;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public FormattedQuote withName(String name) {
this.name = name;
return this;
}

public String getShortName() {
return shortName;
}

public void setShortName(String shortName) {
this.shortName = shortName;
}

public FormattedQuote withShortName(String shortName) {
this.shortName = shortName;
return this;
}

public String getOnAirName() {
return onAirName;
}

public void setOnAirName(String onAirName) {
this.onAirName = onAirName;
}

public FormattedQuote withOnAirName(String onAirName) {
this.onAirName = onAirName;
return this;
}

public String getAltName() {
return altName;
}

public void setAltName(String altName) {
this.altName = altName;
}

public FormattedQuote withAltName(String altName) {
this.altName = altName;
return this;
}

public String getLast() {
return last;
}

public void setLast(String last) {
this.last = last;
}

public FormattedQuote withLast(String last) {
this.last = last;
return this;
}

public String getLastTimedate() {
return lastTimedate;
}

public void setLastTimedate(String lastTimedate) {
this.lastTimedate = lastTimedate;
}

public FormattedQuote withLastTimedate(String lastTimedate) {
this.lastTimedate = lastTimedate;
return this;
}

public String getLastTime() {
return lastTime;
}

public void setLastTime(String lastTime) {
this.lastTime = lastTime;
}

public FormattedQuote withLastTime(String lastTime) {
this.lastTime = lastTime;
return this;
}

public String getChangetype() {
return changetype;
}

public void setChangetype(String changetype) {
this.changetype = changetype;
}

public FormattedQuote withChangetype(String changetype) {
this.changetype = changetype;
return this;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public FormattedQuote withType(String type) {
this.type = type;
return this;
}

public String getSubType() {
return subType;
}

public void setSubType(String subType) {
this.subType = subType;
}

public FormattedQuote withSubType(String subType) {
this.subType = subType;
return this;
}

public String getExchange() {
return exchange;
}

public void setExchange(String exchange) {
this.exchange = exchange;
}

public FormattedQuote withExchange(String exchange) {
this.exchange = exchange;
return this;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public FormattedQuote withSource(String source) {
this.source = source;
return this;
}

public String getOpen() {
return open;
}

public void setOpen(String open) {
this.open = open;
}

public FormattedQuote withOpen(String open) {
this.open = open;
return this;
}

public String getHigh() {
return high;
}

public void setHigh(String high) {
this.high = high;
}

public FormattedQuote withHigh(String high) {
this.high = high;
return this;
}

public String getLow() {
return low;
}

public void setLow(String low) {
this.low = low;
}

public FormattedQuote withLow(String low) {
this.low = low;
return this;
}

public String getChange() {
return change;
}

public void setChange(String change) {
this.change = change;
}

public FormattedQuote withChange(String change) {
this.change = change;
return this;
}

public String getChangePct() {
return changePct;
}

public void setChangePct(String changePct) {
this.changePct = changePct;
}

public FormattedQuote withChangePct(String changePct) {
this.changePct = changePct;
return this;
}

public String getCurrencyCode() {
return currencyCode;
}

public void setCurrencyCode(String currencyCode) {
this.currencyCode = currencyCode;
}

public FormattedQuote withCurrencyCode(String currencyCode) {
this.currencyCode = currencyCode;
return this;
}

public String getVolume() {
return volume;
}

public void setVolume(String volume) {
this.volume = volume;
}

public FormattedQuote withVolume(String volume) {
this.volume = volume;
return this;
}

public String getVolumeAlt() {
return volumeAlt;
}

public void setVolumeAlt(String volumeAlt) {
this.volumeAlt = volumeAlt;
}

public FormattedQuote withVolumeAlt(String volumeAlt) {
this.volumeAlt = volumeAlt;
return this;
}

public String getProvider() {
return provider;
}

public void setProvider(String provider) {
this.provider = provider;
}

public FormattedQuote withProvider(String provider) {
this.provider = provider;
return this;
}

public String getPreviousDayClosing() {
return previousDayClosing;
}

public void setPreviousDayClosing(String previousDayClosing) {
this.previousDayClosing = previousDayClosing;
}

public FormattedQuote withPreviousDayClosing(String previousDayClosing) {
this.previousDayClosing = previousDayClosing;
return this;
}

public String getAltSymbol() {
return altSymbol;
}

public void setAltSymbol(String altSymbol) {
this.altSymbol = altSymbol;
}

public FormattedQuote withAltSymbol(String altSymbol) {
this.altSymbol = altSymbol;
return this;
}

public String getRealTime() {
return realTime;
}

public void setRealTime(String realTime) {
this.realTime = realTime;
}

public FormattedQuote withRealTime(String realTime) {
this.realTime = realTime;
return this;
}

public String getCurmktstatus() {
return curmktstatus;
}

public void setCurmktstatus(String curmktstatus) {
this.curmktstatus = curmktstatus;
}

public FormattedQuote withCurmktstatus(String curmktstatus) {
this.curmktstatus = curmktstatus;
return this;
}

public String getPe() {
return pe;
}

public void setPe(String pe) {
this.pe = pe;
}

public FormattedQuote withPe(String pe) {
this.pe = pe;
return this;
}

public String getMktcapView() {
return mktcapView;
}

public void setMktcapView(String mktcapView) {
this.mktcapView = mktcapView;
}

public FormattedQuote withMktcapView(String mktcapView) {
this.mktcapView = mktcapView;
return this;
}

public String getDividend() {
return dividend;
}

public void setDividend(String dividend) {
this.dividend = dividend;
}

public FormattedQuote withDividend(String dividend) {
this.dividend = dividend;
return this;
}

public String getDividendyield() {
return dividendyield;
}

public void setDividendyield(String dividendyield) {
this.dividendyield = dividendyield;
}

public FormattedQuote withDividendyield(String dividendyield) {
this.dividendyield = dividendyield;
return this;
}

public String getBeta() {
return beta;
}

public void setBeta(String beta) {
this.beta = beta;
}

public FormattedQuote withBeta(String beta) {
this.beta = beta;
return this;
}

public String getTendayavgvol() {
return tendayavgvol;
}

public void setTendayavgvol(String tendayavgvol) {
this.tendayavgvol = tendayavgvol;
}

public FormattedQuote withTendayavgvol(String tendayavgvol) {
this.tendayavgvol = tendayavgvol;
return this;
}

public String getPcttendayvol() {
return pcttendayvol;
}

public void setPcttendayvol(String pcttendayvol) {
this.pcttendayvol = pcttendayvol;
}

public FormattedQuote withPcttendayvol(String pcttendayvol) {
this.pcttendayvol = pcttendayvol;
return this;
}

public String getYrhiprice() {
return yrhiprice;
}

public void setYrhiprice(String yrhiprice) {
this.yrhiprice = yrhiprice;
}

public FormattedQuote withYrhiprice(String yrhiprice) {
this.yrhiprice = yrhiprice;
return this;
}

public String getYrhidate() {
return yrhidate;
}

public void setYrhidate(String yrhidate) {
this.yrhidate = yrhidate;
}

public FormattedQuote withYrhidate(String yrhidate) {
this.yrhidate = yrhidate;
return this;
}

public String getYrloprice() {
return yrloprice;
}

public void setYrloprice(String yrloprice) {
this.yrloprice = yrloprice;
}

public FormattedQuote withYrloprice(String yrloprice) {
this.yrloprice = yrloprice;
return this;
}

public String getYrlodate() {
return yrlodate;
}

public void setYrlodate(String yrlodate) {
this.yrlodate = yrlodate;
}

public FormattedQuote withYrlodate(String yrlodate) {
this.yrlodate = yrlodate;
return this;
}

public String getEps() {
return eps;
}

public void setEps(String eps) {
this.eps = eps;
}

public FormattedQuote withEps(String eps) {
this.eps = eps;
return this;
}

public String getSharesout() {
return sharesout;
}

public void setSharesout(String sharesout) {
this.sharesout = sharesout;
}

public FormattedQuote withSharesout(String sharesout) {
this.sharesout = sharesout;
return this;
}

public String getRevenuettm() {
return revenuettm;
}

public void setRevenuettm(String revenuettm) {
this.revenuettm = revenuettm;
}

public FormattedQuote withRevenuettm(String revenuettm) {
this.revenuettm = revenuettm;
return this;
}

public String getROETTM() {
return rOETTM;
}

public void setROETTM(String rOETTM) {
this.rOETTM = rOETTM;
}

public FormattedQuote withROETTM(String rOETTM) {
this.rOETTM = rOETTM;
return this;
}

public String getNETPROFTTM() {
return nETPROFTTM;
}

public void setNETPROFTTM(String nETPROFTTM) {
this.nETPROFTTM = nETPROFTTM;
}

public FormattedQuote withNETPROFTTM(String nETPROFTTM) {
this.nETPROFTTM = nETPROFTTM;
return this;
}

public String getGROSMGNTTM() {
return gROSMGNTTM;
}

public void setGROSMGNTTM(String gROSMGNTTM) {
this.gROSMGNTTM = gROSMGNTTM;
}

public FormattedQuote withGROSMGNTTM(String gROSMGNTTM) {
this.gROSMGNTTM = gROSMGNTTM;
return this;
}

public String getTTMEBITD() {
return tTMEBITD;
}

public void setTTMEBITD(String tTMEBITD) {
this.tTMEBITD = tTMEBITD;
}

public FormattedQuote withTTMEBITD(String tTMEBITD) {
this.tTMEBITD = tTMEBITD;
return this;
}

public String getDEBTEQTYQ() {
return dEBTEQTYQ;
}

public void setDEBTEQTYQ(String dEBTEQTYQ) {
this.dEBTEQTYQ = dEBTEQTYQ;
}

public FormattedQuote withDEBTEQTYQ(String dEBTEQTYQ) {
this.dEBTEQTYQ = dEBTEQTYQ;
return this;
}

public String getFpe() {
return fpe;
}

public void setFpe(String fpe) {
this.fpe = fpe;
}

public FormattedQuote withFpe(String fpe) {
this.fpe = fpe;
return this;
}

public String getFeps() {
return feps;
}

public void setFeps(String feps) {
this.feps = feps;
}

public FormattedQuote withFeps(String feps) {
this.feps = feps;
return this;
}

public String getStreamable() {
return streamable;
}

public void setStreamable(String streamable) {
this.streamable = streamable;
}

public FormattedQuote withStreamable(String streamable) {
this.streamable = streamable;
return this;
}

public String getIssueId() {
return issueId;
}

public void setIssueId(String issueId) {
this.issueId = issueId;
}

public FormattedQuote withIssueId(String issueId) {
this.issueId = issueId;
return this;
}

public String getIssuerId() {
return issuerId;
}

public void setIssuerId(String issuerId) {
this.issuerId = issuerId;
}

public FormattedQuote withIssuerId(String issuerId) {
this.issuerId = issuerId;
return this;
}

public String getCountryCode() {
return countryCode;
}

public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

public FormattedQuote withCountryCode(String countryCode) {
this.countryCode = countryCode;
return this;
}

public String getTimeZone() {
return timeZone;
}

public void setTimeZone(String timeZone) {
this.timeZone = timeZone;
}

public FormattedQuote withTimeZone(String timeZone) {
this.timeZone = timeZone;
return this;
}

}

