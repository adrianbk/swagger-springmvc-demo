import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities

import static org.openqa.selenium.remote.DesiredCapabilities.chrome

waiting {

  timeout = 2
}

baseUrl = "http://localhost:9080/spring3-testsuite/"

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

  phantom {
    driver = {
      def d = new PhantomJSDriver(new DesiredCapabilities())
      d.manage().window().setSize(new Dimension(1028, 768))
      d
    }
  }

}
