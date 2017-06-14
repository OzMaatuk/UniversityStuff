From L.Schomaker@ai.rug.nl Wed Nov 26 19:01:20 2003
Return-Path: <L.Schomaker@ai.rug.nl>
Received: from ai.rug.nl (psyX7.ai.rug.nl [129.125.139.207])
	by tcw2.ppsw.rug.nl (8.12.8/8.12.8) with ESMTP id hAQI1MOj007955
	for <bulacu@ai.rug.nl>; Wed, 26 Nov 2003 19:01:22 +0100
Message-ID: <3FC4EA70.5060806@ai.rug.nl>
Date: Wed, 26 Nov 2003 19:01:20 +0100
From: Lambert Schomaker <L.Schomaker@ai.rug.nl>
User-Agent: Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.2.1) Gecko/20030225
X-Accept-Language: en-us, en
MIME-Version: 1.0
To: bulacu@ai.rug.nl
Subject: gtk pixbuf format for image processing
Content-Type: text/plain;
  charset=us-ascii;
  format=flowed
Content-Transfer-Encoding: 7bit
X-ai-MailScanner-Information: Please contact helpdesk@ai.rug.nl for more information
X-ai-MailScanner: Found to be clean
X-ai-MailScanner-SpamCheck: not spam (whitelisted),
	SpamAssassin (score=-100.5, required 4, SPAM_PHRASE_00_01 0.78,
	USER_AGENT -0.52, USER_AGENT_MOZILLA_UA -0.60,
	USER_IN_WHITELIST -100.00, X_ACCEPT_LANG -0.21)
Status: RO
X-Status: U
X-KMail-EncryptionState:  
X-KMail-SignatureState:  

Hello Marius,

if you want to modify pixels in a pixbuf in gtk/gdk
it is easy. The following example makes the upper
left 100x100 pixels green

/* .................................... */

void process_pixbuf(GdkPixbuf *mypixbuf)
{
    guchar *raw_image;
    gboolean has_alpha;
    gint rowstride, height, width;
    int y,x,k;

    raw_image = gdk_pixbuf_get_pixels (mypixbuf);
    has_alpha = gdk_pixbuf_get_has_alpha (mypixbuf);
    rowstride = gdk_pixbuf_get_rowstride (mypixbuf);
    height    = gdk_pixbuf_get_height (mypixbuf);
    width     = gdk_pixbuf_get_width (mypixbuf);

    for(y = 0; y < 100; ++y) {
       for(x = 0; x < 100; ++x) {
           k = y * rowstride + (x * (has_alpha ? 4 : 3));
           raw_image[k++] = 0;      /* Red */
           raw_image[k++] = 255;    /* Green */
           raw_image[k++] = 0;      /* Blue */
             /* If has_alpha == TRUE, then the alpha component is in
                   pixels [offset + 3] */
       }
    }
}

/* .................................... */


