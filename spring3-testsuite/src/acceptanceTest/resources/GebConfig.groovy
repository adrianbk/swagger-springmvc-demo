import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

import static org.openqa.selenium.remote.DesiredCapabilities.chrome

waiting {
  timeout = 4
}
baseUrl = "http://localhost:9080/spring3-testsuite/"

environments {
  chrome {
    driver = {
      def chromeDriver = new ChromeDriver()
      chromeDriver.manage().window().setSize(new Dimension(1280, 1024))
      chromeDriver
    }
  }

  firefox {
    driver = {
      def ffDriver = new FirefoxDriver()
      ffDriver.manage().window().setSize(new Dimension(1280, 1024))
      ffDriver
    }
  }
}
