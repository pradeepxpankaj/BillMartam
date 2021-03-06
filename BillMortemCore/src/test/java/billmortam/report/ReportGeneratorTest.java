package billmortam.report;


import billmortam.parser.HdfcBillParserTest;
import billmortam.pdf.Pdf;
import billmortam.transaction.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class ReportGeneratorTest {

    @Test
    public void generate_report_all(){
        ReportGenerator genrator = ReportGenerator.getGenerator();
        Pdf pdf = new Pdf();
        pdf.setData(HdfcBillParserTest.raw);
        TransactionReport transactions = genrator.genrateFrom(pdf);

        Assert.assertTrue(transactions.getContents().size() > 0);
    }

    @Test
    public void generate_report_on_search(){
        ReportGenerator genrator = ReportGenerator.getGenerator();
        Pdf pdf = new Pdf();
        pdf.setData(HdfcBillParserTest.raw);
        TransactionReport transactions = genrator.genrateFrom(pdf);

        TransactionReport searchedTransactions = genrator.generateFor("PAYTM", transactions);
        Assert.assertTrue(searchedTransactions.getContents().size() > 0);
    }
    @Test
    public void calculate_total(){
        ReportGenerator genrator = ReportGenerator.getGenerator();
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        TransactionReport searchedTransactions = genrator.generateFor("PAYTM", transactionReport);
        double total = genrator.getTotalExpenditure(searchedTransactions);
        double actual = 264.00;
        Assert.assertEquals(actual+"is the result",actual, total,0.0d);
    }
    @Test
    public void calculate_total_all(){
        ReportGenerator genrator = ReportGenerator.getGenerator();
        Pdf pdf = new Pdf();
        pdf.setData(HdfcBillParserTest.raw);
        TransactionReport transactions = genrator.genrateFrom(pdf);
        TransactionReport searchedTransactions = genrator.generateFor("AMITA", transactions);
        double total = genrator.getTotalExpenditure(searchedTransactions);
        double actual = 857.6999816894531;
        Assert.assertEquals(actual+"is the result",actual, total,0.0d);
    }




}
