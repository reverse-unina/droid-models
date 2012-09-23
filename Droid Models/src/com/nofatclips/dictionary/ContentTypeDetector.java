package com.nofatclips.dictionary;

import static com.nofatclips.dictionary.InputType.*;

import com.nofatclips.androidtesting.model.ContentType;
import com.nofatclips.androidtesting.model.WidgetState;

public class ContentTypeDetector {
	
	public static boolean isText (int type) {
        if ((type & TYPE_MASK_CLASS) != TYPE_CLASS_TEXT) {
            return false;
        }

        return true;
    }

    public static boolean isNumber (int type) {
        if ((type & TYPE_MASK_CLASS) != TYPE_CLASS_NUMBER) {
            return false;
        }

        return true;
    }

    public static boolean isDatetime (int type) {
        if ((type & TYPE_MASK_CLASS) != TYPE_CLASS_DATETIME) {
            return false;
        }

        return true;
    }

    public static boolean isPhone (int type) {
        if ((type & TYPE_MASK_CLASS) != TYPE_CLASS_PHONE) {
            return false;
        }

        return true;
    }

    public static boolean isNumberSigned (int type) {
        if (!isNumber(type) || ((type & TYPE_MASK_FLAGS) != TYPE_NUMBER_FLAG_SIGNED)) {
            return false;
        }

        return true;
    }

    public static boolean isNumberDecimal (int type) {
        if (!isNumber(type) || ((type & TYPE_MASK_FLAGS) != TYPE_NUMBER_FLAG_DECIMAL)) {
            return false;
        }

        return true;
    }

    public static boolean isTextEmailAddress (int type) {
        if (!isText(type) || ((type & TYPE_MASK_VARIATION) != TYPE_TEXT_VARIATION_EMAIL_ADDRESS)) {
            return false;
        }

        return true;
    }

    public static boolean isTextPostalAddress (int type) {
        if (!isText(type) || ((type & TYPE_MASK_VARIATION) != TYPE_TEXT_VARIATION_POSTAL_ADDRESS)) {
            return false;
        }

        return true;
    }

    public static boolean isTextURI (int type) {
        if (!isText(type) || ((type & TYPE_MASK_VARIATION) != TYPE_TEXT_VARIATION_URI)) {
            return false;
        }

        return true;
    }

    public static boolean isTextMultiline (int type) {
        if (!isText(type) || ((type & TYPE_MASK_FLAGS) != TYPE_TEXT_FLAG_MULTI_LINE)) {
            return false;
        }

        return true;
    }
    
    public static String detect(WidgetState widget, String txtId)
    {
    	int type = Integer.valueOf(widget.getTextType());
        String nameLowerCase = widget.getName().toLowerCase();
        String txtIdLowerCase = (txtId != null)?txtId.toLowerCase():null;
        String valueLowerCase = widget.getValue().toLowerCase();
        
        if (isText(type) || isTextURI(type) || isTextEmailAddress(type) || isTextPostalAddress(type))
        {
	        if (	isTextURI(type)
	        	||	stringContains(valueLowerCase, "http", "www")
	        	||	stringContains(nameLowerCase, "site", "url", "web", "sito")
	        	||	stringContains(txtIdLowerCase, "site", "url", "web", "sito")
	        )
	        {
	        	return ContentType.URL;
	        }
	    	
	        else if (	isTextEmailAddress(type)
		        	||	stringContains(valueLowerCase, "@")
		        	||	stringContains(nameLowerCase, "e-mail", "email")
		        	||	stringContains(txtIdLowerCase, "e-mail", "email")
	    	)
	    	{
	    		return ContentType.EMAIL;
	    	}
	
	        else if (	stringContains(nameLowerCase, "isbn")
	        		||	stringContains(txtIdLowerCase, "isbn")
	        )
	    	{
	    		return ContentType.ISBN;
	    	}
	        
	        else if (	stringContains(nameLowerCase, "credit", "card")
	        		||	stringContains(txtIdLowerCase, "credit", "card")
	        )
	    	{
	    		return ContentType.CREDIT_CARD;
	    	}	        
	        else if (	isTextPostalAddress(type)
	        		||	stringContains(nameLowerCase, "postcode", "postal", "zip")
	        		||	stringContains(txtIdLowerCase, "postcode", "postal", "zip")
	        )
	    	{
	    		return ContentType.ZIP;
	    	}
        }
        else if (isNumber(type))
        {
        	if (isNumberSigned(type))
        	{
        		if (isNumberDecimal(type))
            	{
        			return ContentType.NUMBER_SIGNED_DECIMAL;
            	}
        		else
        		{
        			return ContentType.NUMBER_SIGNED;
        		}
        	}
        	else if (isNumberDecimal(type))
        	{
        		return ContentType.NUMBER_DECIMAL;
        	}
        	else
        	{
        		return ContentType.NUMBER;
        	}
        }
        
    	return ContentType.DEFAULT;
    }
    
    public static boolean isTextMultiline(WidgetState widget)
    {
    	return isTextMultiline(Integer.valueOf(widget.getTextType()));
    }
    
    public static boolean stringContains(String string, String ... strings)
    {
    	if (string != null)
    	{
	    	for (String s : strings)
	    		if (string.contains(s))
	    			return true;
    	}
    	
    	return false;
    }
}
