package translate;

import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SouperScraper
{ 
  //static { RiTa.init(); }
  
  public static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.56 Safari/536.5";
  
  private static final String TIMEOUT = "timeout";
  private static final String REFERRER = "referrer";
  private static final String USER_AGENT = "userAgent";
  private static final String FOLLOW_REDIRECTS = "followRedirects";
  private static final String IGNORE_CONTENT_TYPE = "ignoreContentType";
  private static final String IGNORE_HTTP_ERRORS = "ignoreHttpErrors";
  
  public Connection conn;
  public Document doc;
  public Map<String, String> options, cookies, headers, data;

  public SouperScraper()
  {
    data = new HashMap<String, String>();
    options = new HashMap<String, String>();
    cookies = new HashMap<String, String>();
    headers = new HashMap<String, String>();
    
    options.put(USER_AGENT, DEFAULT_USER_AGENT); // sent by default (call userAgent(null); to disable)
  }

  public SouperScraper connect(String url)
  {
    conn = Jsoup.connect(url);
    return this;
  }

  public SouperScraper userAgent(String userAgent)
  {
    if (userAgent == null)
      options.remove(USER_AGENT);
    else
      options.put(USER_AGENT, userAgent);
    
    return this;
  }
  
  public SouperScraper referrer(String referrer)
  {
    if (referrer == null)
      options.remove(REFERRER);
    else
      options.put(REFERRER, referrer);
    
    return this;
  }


  public SouperScraper timeout(int millis)
  {
    options.put(TIMEOUT, "" + millis);
    return this;
  }

  public SouperScraper followRedirects(boolean followRedirects)
  {
    options.put(FOLLOW_REDIRECTS, followRedirects + "");
    return this;
  }

  public SouperScraper ignoreHttpErrors(boolean ignoreHttpErrors)
  {
    options.put(IGNORE_HTTP_ERRORS, ignoreHttpErrors + "");
    return this;
  }

  public SouperScraper ignoreContentType(boolean ignoreContentType)
  {
    options.put(IGNORE_CONTENT_TYPE, ignoreContentType + "");
    return this;
  }

  public SouperScraper data(String key, String value) // TODO: test
  {
    if (value == null)
      options.remove(key);
    else
      options.put(key, value);
    
    return this;
  }

  public SouperScraper data(Map<String, String> dataMap) // TODO: test
  {
    cookies.putAll(dataMap);
    return this;
  }

  // a=b&c=d&e=f... ?
  public SouperScraper data(String keyvals) // TODO: finish
  {
    if (1==1) throw new RuntimeException("Not yet implemented");
    return this;
  }

  public SouperScraper header(String name, String value)
  {
    if (value == null)
      headers.remove(name);
    else 
      headers.put(name, value);
    
    return this;
  }

  public SouperScraper cookie(String name, String value)
  {
    if (value == null)
      cookies.remove(name);
    else 
      cookies.put(name, value);

    return this;
  }

  public SouperScraper cookies(Map<String, String> cookieMap)
  {
    cookies.putAll(cookieMap);
    return this;
  }

  private SouperScraper prepareConnection()
  {
    if (conn == null)
      throw new RuntimeException("Null Connection: Call connect(url) first!");
    
    setCookies().setHeaders().setOptions();
    
    return this;
  }

  private SouperScraper setOptions()
  {
    if (options.containsKey(TIMEOUT)) {
      conn.timeout(Integer.parseInt(options.get(TIMEOUT)));
    }  
    if (options.containsKey(REFERRER)) {
      conn.referrer(options.get(REFERRER));
    }  
    if (options.containsKey(USER_AGENT)) {
      conn.userAgent(options.get(USER_AGENT));
    }  
    if (options.containsKey(FOLLOW_REDIRECTS)) {
      conn.followRedirects(Boolean.parseBoolean(options.get(FOLLOW_REDIRECTS)));
    }
    if (options.containsKey(IGNORE_HTTP_ERRORS)) {
      conn.ignoreHttpErrors(Boolean.parseBoolean(options.get(IGNORE_HTTP_ERRORS)));
    }
    if (options.containsKey(IGNORE_CONTENT_TYPE)) {
      conn.ignoreContentType(Boolean.parseBoolean(options.get(IGNORE_CONTENT_TYPE)));
    } 
    
    return this;
  }

  private SouperScraper setHeaders()
  {
    for (Iterator it = headers.keySet().iterator(); it.hasNext();)
    {
      String key = (String) it.next();
      conn.header(key, headers.get(key));
    }
    return this;
  }

  private SouperScraper setCookies()
  {
    for (Iterator it = cookies.keySet().iterator(); it.hasNext();)
    {
      String key = (String) it.next();
      conn.cookie(key, cookies.get(key));
    }
    return this;
  }
  
  private SouperScraper setPostData()
  {
    for (Iterator it = data.keySet().iterator(); it.hasNext();)
    {
      String key = (String) it.next();
      conn.data(key, data.get(key));
    }
    return this;
  }

  public String get(/*String url*/)
  {
    prepareConnection();//.connect(url);

    try
    {
      doc = conn.get();
    }
    catch (IOException e)
    {
      System.out.println(conn.response());
      throw new RuntimeException(e);
    }

    return doc.outerHtml();
  }
  
  public String body()
  {
    prepareConnection();

    try
    {
      doc = conn.get();

    }
    catch (IOException e)
    {
      System.out.println(conn.response());
      throw new RuntimeException(e);
    }

    return doc.body().ownText();
  }
  
  public String head()
  {
    prepareConnection();

    try
    {
      doc = conn.get();

    }
    catch (IOException e)
    {
      System.out.println(conn.response());
      throw new RuntimeException(e);
    }

    return doc.head().ownText();
  }
    
  public String post(/*String url*/) 
  {
    prepareConnection();//.connect(url);
    setPostData();
    
    try
    {
      doc = conn.post();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

    return doc.outerHtml();
  }
  public static void main(String[] args)
  {
    /*fetchCookies("http://google.com");
    //loadCookiesFromDisk();
    if (1==1) return;*/



    SouperScraper scraper = new SouperScraper();
    scraper.header("User-Agent", DEFAULT_USER_AGENT).
      header("Accept-Language", "en-US,en.q=0.8").
      header("Accept-Charset", "ISO-8859-1,utf-8.q=0.7,*.q=0.3").
      header("Connection", "keep-alive").
      header("Accept","text/html,application/xhtml+xml,application/xml.q=0.9,*/*.q=0.8");
      //header("Cookie", cookie).
      //header("DNT", "1");
      //scraper.cookie("PREF", value);
    System.out.println(scraper.connect("http://rednoise.org/wdmtest.html").get());
  }

}
