package com.deere.democlient.util;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;

public class EmailWriter {
    public String emailBody(String userName, String fullName, String orgID, String orgName, String toolID, String productName) {
        return "Requesting Account Name:   " + userName + "  (" + fullName + ")\nOrganization ID:                          " + orgID + "  (" + orgName + ")\n\nTool IDs:                                         " + toolID + "\nProduct Names:                          " + productName;
    }
    public void openEmail(String body) throws Exception {
        if(!Desktop.isDesktopSupported()) {
            throw new Exception("Desktop is not Supported.");
        }
        Desktop desktop = Desktop.getDesktop();
        body = URLEncoder.encode(body, "UTF-8").replace("+","%20");
        URI mailTo = new URI("mailto:APIDevSupport@JohnDeere.com?subject=API%20Support%20Service%20Request&body="+body);
        desktop.mail(mailTo);
    }
}