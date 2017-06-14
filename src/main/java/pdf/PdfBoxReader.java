package pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import util.Util;

import java.io.File;
import java.io.IOException;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class PdfBoxReader implements PdfReader {

    private PdfBoxReader() {
    }

    @Override
    public String read(String fileUrl) throws PdfReaderException {
        if (isPdfUrl(fileUrl)) {
            PDDocument document = null;
            try {
                File file = new File(fileUrl);
                if (isPasswordProtected(file)) {
                    throw new PdfReaderException(PdfReaderException.ExceptionType.PASSWORD_PROTECTED);
                }
                document = PDDocument.load(file);
                return getPdfContent(document);
            } catch (IOException e) {
                System.out.print(e.getLocalizedMessage());
            } finally {
                closeDocument(document);
            }
        } else {
            throw new PdfReaderException(PdfReaderException.ExceptionType.INVALID_PDF_FILE);
        }
        return null;
    }

    private void closeDocument(PDDocument document) {
        if (document != null) {
            try {
                document.getDocument().close();
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String read(String path, String password) throws PdfReaderException {
        if (isPdfUrl(path)) {
            File file = new File(path);
            PDDocument document = null;
            try {
                document = PDDocument.load(file, password);
                document.isAllSecurityToBeRemoved();
                return getPdfContent(document);
            } catch (InvalidPasswordException e) {
                throw new PdfReaderException(PdfReaderException.ExceptionType.INVALID_PASSWORD);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeDocument(document);
            }
        }
        return null;
    }

    private String getPdfContent(PDDocument document) throws IOException {
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        return pdfTextStripper.getText(document);
    }

    private boolean isPasswordProtected(File file) {
        PDDocument pd;
        try {
            pd = PDDocument.load(file);
        } catch (IOException e) {
            return e.getMessage().equals("Cannot decrypt PDF, the password is incorrect");
        }
        return pd.isEncrypted();
    }

    private boolean isPdfUrl(String url) {
        return Util.checkPdfUrl(url);
    }

    public static PdfReader getReader() {
        return new PdfBoxReader();
    }
}
