package org.asamk.signal.commands;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

import org.asamk.signal.commands.exceptions.CommandException;
import org.asamk.signal.commands.exceptions.IOErrorException;
import org.asamk.signal.commands.exceptions.UserErrorException;
import org.asamk.signal.manager.ProvisioningManager;
import org.asamk.signal.manager.api.UserAlreadyExistsException;
import org.asamk.signal.output.OutputWriter;
import org.asamk.signal.output.PlainTextWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class LinkCommand implements ProvisioningCommand {

    private static final Logger logger = LoggerFactory.getLogger(LinkCommand.class);

    @Override
    public String getName() {
        return "link";
    }

    @Override
    public void attachToSubparser(final Subparser subparser) {
        subparser.help("Link to an existing device, instead of registering a new number.");
        subparser.addArgument("-n", "--name").help("Specify a name to describe this new device.");
    }

    @Override
    public void handleCommand(
            final Namespace ns,
            final ProvisioningManager m,
            final OutputWriter outputWriter
    ) throws CommandException {
        final PlainTextWriter writer = (PlainTextWriter) outputWriter;

        String deviceName = ns.getString("name");
        if (deviceName == null) {
            deviceName = "cli";
        }
        try {
            final URI uri = m.getDeviceLinkUri();
            writer.println("{}", uri);
            try {
                printQrCode(writer, uri);
            } catch (WriterException e) {
                logger.debug("Failed to generate QR code: {}", e.getMessage());
            }
            final String number = m.finishDeviceLink(deviceName);
            writer.println("Associated with: {}", number);
        } catch (TimeoutException e) {
            throw new UserErrorException("Link request timed out, please try again.");
        } catch (IOException e) {
            throw new IOErrorException("Link request error: " + e.getMessage(), e);
        } catch (UserAlreadyExistsException e) {
            throw new UserErrorException("The user "
                    + e.getNumber()
                    + " already exists\nDelete \""
                    + e.getFileName()
                    + "\" before trying again.");
        }
    }

    public static void main(String[] args) throws WriterException, IOException {
        final String testUri = "baxs://linkdevice?uuid=test-device-uuid&pub_key=dGVzdHB1YmxpY2tleQ==";
        final PlainTextWriter writer = new org.asamk.signal.output.PlainTextWriterImpl(new OutputStreamWriter(System.out));
        printQrCode(writer, URI.create(testUri));
    }

    private static void printQrCode(PlainTextWriter writer, final URI uri) throws WriterException, IOException {
        final Map<EncodeHintType, Object> hints = Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L,
                EncodeHintType.MARGIN, 2);
        final BitMatrix matrix = new QRCodeWriter().encode(uri.toString(), BarcodeFormat.QR_CODE, 0, 0, hints);
        final StringBuilder sb = new StringBuilder();
        final boolean ansi = System.console() != null;
        final String white = ansi ? "\033[107;30m" : "";
        final String reset = ansi ? "\033[0m" : "";
        for (int y = 0; y < matrix.getHeight(); y += 2) {
            sb.append(white);
            for (int x = 0; x < matrix.getWidth(); x++) {
                final boolean top = matrix.get(x, y);
                final boolean bottom = (y + 1 < matrix.getHeight()) && matrix.get(x, y + 1);
                final char ch;
                if (top && bottom) {
                    ch = '█';
                } else if (top) {
                    ch = '▀';
                } else if (bottom) {
                    ch = '▄';
                } else {
                    ch = ' ';
                }
                sb.append(ch);
            }
            sb.append(reset).append('\n');
        }
        writer.println("{}", sb.toString());
    }
}
