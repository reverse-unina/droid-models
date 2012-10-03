package com.nofatclips.dictionary;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.nofatclips.androidtesting.model.ContentType;
import com.nofatclips.androidtesting.model.WidgetState;

public class TestValuesDictionary
{
	public static Random random = new Random();
	
	public static String[] getRandomValue(WidgetState widget)
	{
		String contentType = widget.getContentType();
		
		if (contentType.equals(ContentType.EMAIL))
		{
			return getRandomValue(EMAIL_valid, EMAIL_invalid);
		}
		else if(contentType.equals(ContentType.URL))
		{
			return getRandomValue(URL_valid, URL_invalid);			
		}
		else if(contentType.equals(ContentType.ZIP))
		{
			return getRandomValue(ZIP_valid, ZIP_invalid);
		}
		else if(contentType.equals(ContentType.ISBN))
		{
			return getRandomValue(ISBN_valid, ISBN_invalid);
		}
		else if(contentType.equals(ContentType.CREDIT_CARD))
		{
			return getRandomValue(CREDIT_CARD_valid, CREDIT_CARD_invalid);			
		}
		else if(contentType.equals(ContentType.NUMBER))
		{
			return getRandomValue(NUMBER_valid, NUMBER_invalid);			
		}
		else if(contentType.equals(ContentType.NUMBER_DECIMAL))
		{
			return getRandomValue(NUMBER_DECIMAL_valid, NUMBER_DECIMAL_invalid);
		}
		else if(contentType.equals(ContentType.NUMBER_SIGNED))
		{
			return getRandomValue(NUMBER_SIGNED_valid, NUMBER_SIGNED_invalid);
		}
		else if(contentType.equals(ContentType.NUMBER_SIGNED_DECIMAL))
		{
			return getRandomValue(NUMBER_SIGNED_DECIMAL_valid, NUMBER_SIGNED_DECIMAL_invalid);
		}
		else	
		{
			return getRandomValue(DEFAULT_STRINGS, DEFAULT_STRINGS);
		}
	}
	
	public static String[] getRandomValue(String[] valid, String[] invalid)
	{
		String[] mix = new String[valid.length + invalid.length];
		
		int i = 0; //riusato nel for successivo per il merge
		for (; i < valid.length; i++)
			mix[i] = valid[i];
		
		for (int j = 0; j < invalid.length; j++)
			mix[i++] = invalid[j];
		
		Collections.shuffle(Arrays.asList(mix));
		
		if (mix.length > 1)
		{
			String[] ret = { mix[0], mix[1] };
			return ret;
		}
		else if (mix.length > 0)
		{
			String[] ret = { mix[0], mix[0] };
			return ret;
		}
		else
		{
			return null;
		}
	}
	
	public static String[] getValues(WidgetState widget, boolean firstValue)
	{
		String contentType = widget.getContentType();
		
		if (contentType.equals(ContentType.EMAIL))
		{
			return getValues(EMAIL_valid, EMAIL_invalid, firstValue);
		}
		else if(contentType.equals(ContentType.URL))
		{
			return getValues(URL_valid, URL_invalid, firstValue);			
		}
		else if(contentType.equals(ContentType.ZIP))
		{
			return getValues(ZIP_valid, ZIP_invalid, firstValue);
		}
		else if(contentType.equals(ContentType.ISBN))
		{
			return getValues(ISBN_valid, ISBN_invalid, firstValue);
		}
		else if(contentType.equals(ContentType.CREDIT_CARD))
		{
			return getValues(CREDIT_CARD_valid, CREDIT_CARD_invalid, firstValue);			
		}
		else if(contentType.equals(ContentType.NUMBER))
		{
			return getValues(NUMBER_valid, NUMBER_invalid, firstValue);			

		}
		else if(contentType.equals(ContentType.NUMBER_DECIMAL))
		{
			return getValues(NUMBER_DECIMAL_valid, NUMBER_DECIMAL_invalid, firstValue);
		}
		else if(contentType.equals(ContentType.NUMBER_SIGNED))
		{
			return getValues(NUMBER_SIGNED_valid, NUMBER_SIGNED_invalid, firstValue);
		}
		else if(contentType.equals(ContentType.NUMBER_SIGNED_DECIMAL))
		{
			return getValues(NUMBER_SIGNED_DECIMAL_valid, NUMBER_SIGNED_DECIMAL_invalid, firstValue);
		}
		else	
		{
			return getValues(DEFAULT_STRINGS, DEFAULT_STRINGS, firstValue); 
		}
	}
	
	public final static String[] getValues(String[] valid, String[] invalid, boolean firstValue)
	{
		String[] ret = new String[2];
		if (firstValue)
		{
			ret[0] = invalid[0];
			ret[1] = valid[0];
		}
		else
		{
			int valid_idx = TestValuesDictionary.random.nextInt(valid.length - 1);
			int invalid_idx = TestValuesDictionary.random.nextInt(invalid.length - 1);
			
			ret[0] = invalid[invalid_idx];
			ret[1] = valid[valid_idx];
		}
		
		return ret;
	}
	
	public final static String[] EMAIL_valid = {
		"email@domain.com", // Valid email
		"firstname.lastname@domain.com", // Email contains dot in the address field
		"email@subdomain.domain.com", // Email contains dot with subdomain
		"firstname+lastname@domain.com", // Plus sign is considered valid character
		"email@123.123.123.123", // Domain is valid IP address
		"email@[123.123.123.123]", // Square bracket around IP address is considered valid
		"\"email\"@domain.com", // Quotes around email is considered valid
		"1234567890@domain.com", // Digits in address are valid
		"email@domain-one.com", // Dash in domain name is valid
		"_______@domain.com", // Underscore in the address field is valid
		"email@domain.name", // .name is valid Top Level Domain name
		"email@domain.co.jp", // Dot in Top Level Domain name also considered valid (use co.jp as example here)
		"firstname-lastname@domain.com" // Dash in address field is valid
	};
	
	public final static String[] EMAIL_invalid = {
		"plainaddress", // Missing @ sign and domain
		"#@%^%#$@#$@#.com", // Garbage
		"@domain.com", // Missing username
		"Joe Smith <email@domain.com>", // Encoded html within email is invalid
		"email.domain.com", // Missing @
		"email@domain@domain.com", // Two @ sign
		".email@domain.com", // Leading dot in address is not allowed
		"email.@domain.co", // Trailing dot in address is not allowed
		"email..email@domain.com", // Multiple dots
		"あいうえお@domain.com", // Unicode char as address
		"email@domain.com (Joe Smith)", // Text followed email is not allowed
		"email@domain", // Missing top level domain (.com/.net/.org/etc)
		"email@-domain.com", // Leading dash in front of domain is invalid
		"email@domain.web", // .web is not a valid top level domain
		"email@111.222.333.44444", // Invalid IP format
		"email@domain..com" // Multiple dot in the domain portion is invalid		
	};
	
	//RFC3986 normal example
	public final static String[] URL_valid = {
		"http://www.google.it",
		"g:h",
		"http://a/b/c/g",
		"http://a/b/c/g",
		"http://a/b/c/g/",
		"http://a/g",
		"http://g",
		"http://a/b/c/d;p?y",
		"http://a/b/c/g?y",
		"http://a/b/c/d;p?q#s",
		"http://a/b/c/g#s",
		"http://a/b/c/g?y#s",
		"http://a/b/c/;x",
		"http://a/b/c/g;x",
		"http://a/b/c/g;x?y#s",
		"http://a/b/c/d;p?q",
		"http://a/b/c/",
		"http://a/b/c/",
		"http://a/b/",
		"http://a/b/",
		"http://a/b/g",
		"http://a/",
		"http://a/",
		"http://a/g"
	};
	
	public final static String[] URL_invalid = {
		"http;\\/www;google;it",
		"ht\ttp:@www.google.com:80/;p?#",
		"http:////////user:@google.com:99?foo",
		"http:\\\\\\\\www.google.com\\\\foo",
		"http://foo:-80/",
		"htto;//pippo,com"
	};
	
	public final static String[] ISBN_valid = {
		"1116928000",
		"1284700151",
		"128470016X",
		"1452472319",
		"1452472327",
		"1620244470",
		"1620244489",
		"1788016637",
		"1788016645",
		"7827814395",
		"7827814409",
		"7995586558",
		"7995586566",
		"8163358718",
		"8163358726",
		"8331130871",
		"833113088X",
		"8498903033"		
	};
	
	public final static String[] ISBN_invalid = {
		"816335872U",
		"78278178278144094409",
		"32554744",
		"8163358721",
		"ASDBS!!DFF"
	};
	
	public final static String[] CREDIT_CARD_valid = {
		"5123695007103193", //mastercard
		"4116480559370132", //visa
		"6011976857117225", //discover
		"344058488426266", //amex
		"378282246310005", //American Express
		"371449635398431", //American Express
		"378734493671000", //American Express Corporate
		"5610591081018250", //Australian BankCard
		"30569309025904", //Diners Club
		"38520000023237", //Diners Club
		"6011111111111117", //Discover
		"6011000990139424", //Discover
		"3530111333300000", //JCB
		"3566002020360505 ", //JCB
		"5555555555554444", //MasterCard
		"5105105105105100", //MasterCard
		"4012888888881881", //Visa
		"4222222222222", //Visa
		"76009244561", //Dankort (PBS)
		"5019717010103742", //Dankort (PBS)
		"6331101999990016" //Switch/Solo (Paymentech)		
	};
	
	public final static String[] CREDIT_CARD_invalid = {
		"37828AB46310005",
		"371449621321335398431",
		"378734493671000",
		"-30569309025904",
		"385.20000.023237",
		"6121111111111117",
		"A011000990139424",
		"35000111333300000",
		"3561001120360505",
		"5431255555554444",
		"AAABBB5105105100",
		"411111!111111111",
		"123456",
		"12"
	};

	public final static String[] ZIP_valid = {
		"35801",
		"44101",
		"82941"
	};
	
	public final static String[] ZIP_invalid = {
		"3a5801a",
		"4a4101",
		"829411234",
		"abcdef",
		"!!!!!!"
	};
	
	public final static String[] NUMBER_valid = {
		"1",
		"10",
		"9999",
		"34",
		"40",
		"55",
		"70",
		"99",
		"1000",
		"2988881",
		"1234124",
		"2",
		"33",
		"435"
	};
	
	public final static String[] NUMBER_invalid = {
		"AAA",
		"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999",
		"-12",		
		"!!-",
		"12.01",
		"12%",
		"z<q"
	};
	
	public final static String[] NUMBER_SIGNED_valid = {
		"-1",
		"10",
		"-9999",
		"-34",
		"40",
		"55",
		"70",
		"99",
		"-1000",
		"2988881",
		"-1234124",
		"-2",
		"-33",
		"435"
	};
	
	public final static String[] NUMBER_SIGNED_invalid = {
		"12,3",
		"ab00",
		"99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999",
		"!12",
		"233176401123040024477515224301703382452989758054156037914702679301887293196935329184208300820842156635598983942674598.921047033915219852965519484067098016447",
		"10.-9",
		"hello",
		"09@11+",
		"*12.01",
		"AAA",
		"!!-",
		"12.01",
		"12%",
		"z<q"
	};
	
	public final static String[] NUMBER_DECIMAL_valid = {
		"12.3",
		"122323.3331",
		"0.3331",
		"11.78",
		"12.78",
		"1156.1728",
		"1314.1278",
		"4411.7448",
		"99.9",
		"90.77"
	};
	
	public final static String[] NUMBER_DECIMAL_invalid = {
		"12,3",
		"ab00",
		"233176401123040024477515224301703382452989758054156037914702679301887293196935329184208300820842156635598983942674598.921047033915219852965519484067098016447",
		"10.-9",
		"hello",
		"09@11+",
		"-12",
		"AAA",
		"!!-",
		"*12.01",
		"12.01",
		"12%",
		"z<q"
	};

	public final static String[] NUMBER_SIGNED_DECIMAL_valid = {
		"-12.3",
		"122323.3331",
		"-0.3331",
		"11.78",
		"12.78",
		"1156.1728",
		"-1314.1278",
		"4411.7448",
		"-99.9",
		"-90.77"
	};
	
	public final static String[] NUMBER_SIGNED_DECIMAL_invalid = {
		"12,3",
		"ab00",
		"233176401123040024477515224301703382452989758054156037914702679301887293196935329184208300820842156635598983942674598.921047033915219852965519484067098016447",
		"10.-9",
		"hello",
		"09@11+",
		"*12.01",
		"AAA",
		"!!-",
		"12.01",
		"12%",
		"z<q"
	};

	public final static String[] DEFAULT_STRINGS = {
		"string",
		"zyxel129",
		"cercei",
		"1mxrwiha",
		"hehui2015",
		"jamisdakar",
		"x3cNitoO",
		"langfield345",
		"qch123",
		"19790511",
		"lsxusu0417",
		"U7tw3Fr117",
		"TuMIUc3s",
		"tindersticks",
		"porty45qs",
		"19811983",
		"362137624",
		"mybigtoe",
		"falcons420",
		"abdd870c",
		"ganga-giulia",
		"z885633",
		"123456pk45",
		"zxc52520",
		"qq19890418",
		"huiming",
		"sandisk",
		"3232",
		"4596",
		"lilihappy",
		"angga",
		"herguless",
		"fatchiken",
		"fjqxm0000",
		"sts1903",
		"HAIXIA2009",
		"zou186187"
	};
}
