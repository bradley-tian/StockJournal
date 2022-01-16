/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockjournal;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormattedQuoteResult {

@SerializedName("FormattedQuote")
@Expose
private List<FormattedQuote> formattedQuote = null;

/**
* No args constructor for use in serialization
*
*/
public FormattedQuoteResult() {
}

/**
*
* @param formattedQuote
*/
public FormattedQuoteResult(List<FormattedQuote> formattedQuote) {
super();
this.formattedQuote = formattedQuote;
}

public List<FormattedQuote> getFormattedQuote() {
return formattedQuote;
}

public void setFormattedQuote(List<FormattedQuote> formattedQuote) {
this.formattedQuote = formattedQuote;
}

public FormattedQuoteResult withFormattedQuote(List<FormattedQuote> formattedQuote) {
this.formattedQuote = formattedQuote;
return this;
}

}
