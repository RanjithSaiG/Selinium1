package tanu;

	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class Brokenlink {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.amazon.in/s?k=flipkart.com&crid=D9BZO6MUXCXS&sprefix=flipkart.com%2Caps%2C195&ref=nb_sb_noss_1");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			List<WebElement> links = driver.findElements(By.tagName("a"));

	        for (WebElement link : links) {
	            String url = link.getAttribute("href");
	            if (url != null && !url.isEmpty()) {
	                         try {
	                    
	                    URL linkURL = new URL(url);
	                    HttpURLConnection connection = (HttpURLConnection) linkURL.openConnection();
	                    connection.connect();
	                    int responseCode = connection.getResponseCode();
	                    if (responseCode >= 200) 
	                    {
	                       System.out.println("Broken Link: " + url + " (Response Code: " + responseCode + ")");
	                    }
	                  } catch (Exception e) 
	                {                   
	                    System.out.println("Exception for Link: " + url + " - " + e.getMessage());
	                }
	            }
	        }

	        
	        driver.quit();
	    }

		}

