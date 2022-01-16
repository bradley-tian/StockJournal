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

public class Response {

@SerializedName("FormattedQuoteResult")
@Expose
private FormattedQuoteResult formattedQuoteResult;

/**
* No args constructor for use in serialization
*
*/
public Response() {
}

/**
*
* @param formattedQuoteResult
*/
public Response(FormattedQuoteResult formattedQuoteResult) {
super();
this.formattedQuoteResult = formattedQuoteResult;
}

public FormattedQuoteResult getFormattedQuoteResult() {
return formattedQuoteResult;
}

public void setFormattedQuoteResult(FormattedQuoteResult formattedQuoteResult) {
this.formattedQuoteResult = formattedQuoteResult;
}

public Response withFormattedQuoteResult(FormattedQuoteResult formattedQuoteResult) {
this.formattedQuoteResult = formattedQuoteResult;
return this;
}

}