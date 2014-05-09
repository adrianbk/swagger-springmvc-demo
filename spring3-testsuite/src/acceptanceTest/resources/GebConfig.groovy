import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

import static org.openqa.selenium.remote.DesiredCapabilities.chrome

waiting {

  //Goggle fonts in swagger ui :(
  timeout = 4
}
baseUrl = "http://localhost:9080/spring3-testsuite/"

driver = {
  def ffDriver = new FirefoxDriver()
  ffDriver.manage().window().maximize()
  ffDriver
}

environments {
  chrome {
    driver = {
      def chromeDriver = new ChromeDriver()
      chromeDriver.manage().window().maximize()
      chromeDriver
    }
  }

  firefox {
    driver = {
      def ffDriver = new FirefoxDriver()
      ffDriver.manage().window().maximize()
      ffDriver
    }
  }
}
