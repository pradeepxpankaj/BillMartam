package billmortam.cache;


import billmortam.TimeComplexityTestCase;
import billmortam.report.TransactionReport;
import billmortam.transaction.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 03/07/17.
 */
public class ReportsCacheManagerTest extends TimeComplexityTestCase {
    @Test
    public void test_report_saving(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("temp1");

        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        Assert.assertTrue(cacheManager.save(reportCache));
    }

    @Test
    public void test_report_reading(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("temp1");

        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("temp1");
        Assert.assertTrue(result.getReport().getContents().size() == 4);
    }

    @Test
    public void test_report_overwriting_file(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("temp1");

        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));


        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("temp1");
        Assert.assertTrue(result.getReport().getContents().size() == 11);
    }

    @Test
    public void test_report_writing_file(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/file.pdf");

        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));

        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/file.pdf");
        Assert.assertTrue(result.getReport().getContents().size() == 11);
    }

    @Test
    public void test_report_writing_file1() {
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/file1.pdf");

        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));

        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/file1.pdf");
        Assert.assertTrue(result.getReport().getContents().size() == 10);
    }

    // test for saving password protected file
    @Test
    public void test_report_reading_password_file1() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/password_file1.pdf");
        reportCache.setReport(transactionReport);
        reportCache.setProtectionKey("Pradeep@123");

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/password_file1.pdf");
        Assert.assertTrue(result.getProtectionKey().equals("Pradeep@123"));
    }


    @Test
    public void test_report_reading_unprotected_file() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/password_file1.pdf");
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/password_file1.pdf");
        Assert.assertNull(result.getProtectionKey());
    }

    @Test
    public void deleteFullCache() throws Exception {
        CacheManager cacheManager = ReportsCacheManager.getManager();
        boolean status = cacheManager.clear();
        Assert.assertEquals(true,status);
    }
}
