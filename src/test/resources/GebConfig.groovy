import org.openqa.selenium.chrome.ChromeDriver

def chromeDriver = new File('test/drivers/chrome/chromedriver')
downloadDriver(chromeDriver, "http://chromedriver.storage.googleapis.com/2.9/chromedriver_linux64.zip")

System.setProperty('webdriver.chrome.driver', chromeDriver.absolutePath)

driver = { new ChromeDriver() }
reportsDir = "reports/geb/"
baseUrl = "http://127.0.0.1:8097/swagger-springmvc-demo"

def downloadDriver(File file, String path) {
    if (!file.exists()) {
        def ant = new AntBuilder()
        ant.get(src: path, dest: 'driver.zip')
        ant.unzip(src: 'driver.zip', dest: file.parent)
        ant.delete(file: 'driver.zip')
        ant.chmod(file: file, perm: '700')
    }
}