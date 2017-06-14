From L.Schomaker@ai.rug.nl Mon Oct 27 14:05:59 2003
Return-Path: <L.Schomaker@ai.rug.nl>
Received: from ai.rug.nl (psyX7.ai.rug.nl [129.125.139.207])
	by tcw2.ppsw.rug.nl (8.12.8/8.12.8) with ESMTP id h9RD64qR004999
	for <bulacu@ai.rug.nl>; Mon, 27 Oct 2003 14:06:04 +0100
Message-ID: <3F9D1837.9040109@ai.rug.nl>
Date: Mon, 27 Oct 2003 14:05:59 +0100
From: Lambert Schomaker <L.Schomaker@ai.rug.nl>
User-Agent: Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.2.1) Gecko/20030225
X-Accept-Language: en-us, en
MIME-Version: 1.0
To: bulacu@ai.rug.nl
Subject: The AutoClass Project
Content-Type: multipart/mixed;
  boundary="------------080602030900010502080501"
X-ai-MailScanner-Information: Please contact helpdesk@ai.rug.nl for more information
X-ai-MailScanner: Found to be clean
X-ai-MailScanner-SpamCheck: not spam (whitelisted),
	SpamAssassin (score=-100.6, required 4, MAILTO_LINK 0.20,
	SPAM_PHRASE_01_02 0.50, USER_AGENT -0.52,
	USER_AGENT_MOZILLA_UA -0.60, USER_IN_WHITELIST -100.00,
	X_ACCEPT_LANG -0.21)
Status: RO
X-Status: U
X-KMail-EncryptionState:  
X-KMail-SignatureState:  

This is a multi-part message in MIME format.
--------------080602030900010502080501
Content-Type: text/plain; charset=us-ascii; format=flowed
Content-Transfer-Encoding: 7bit


<http://ic.arc.nasa.gov/ic/projects/bayes-group/autoclass/>

--------------080602030900010502080501
Content-Type: text/html;
 name="ic.arc.nasa.gov/ic/projects/bayes-group/autoclass/"
Content-Transfer-Encoding: 7bit
Content-Disposition: inline;
 filename="ic.arc.nasa.gov/ic/projects/bayes-group/autoclass/"
Content-Base: "http://ic.arc.nasa.gov/ic/projects/bay
	es-group/autoclass/"
Content-Location: "http://ic.arc.nasa.gov/ic/projects/bay
	es-group/autoclass/"

<HTML>

<HEAD>
<TITLE>The AutoClass Project</TITLE>
<!-- Changed by: Will Taylor, 25-Jun-1996 -->
<LINK REV="made" HREF="mailto:william.m.taylor@nasa.gov">
</HEAD>

<BODY>

<H1>The AutoClass Project
</H1>

<IMG SRC = "/img/icons/rainbowline.gif" ALT=""> 
<BR>
<IMG SRC="../images/AC-Shapes-Slide.gif" ALIGN=middle ALT="Before & after groupings of shapes">
<BR>
<IMG SRC = "/img/icons/rainbowline.gif" ALT=""> 
<P>

<A NAME="Contents"><h3>Contents</h3>
 <P>
<UL>
<IMG SRC = "/img/icons/purpleball.gif" ALT=""> <b>Introduction</b> <BR>
<IMG SRC = "/img/icons/purpleball.gif" ALT=""> <a href="autoclass-contacts.html"><b>Contacts</a></b>   <BR>   
<IMG SRC = "/img/icons/purpleball.gif" ALT=""> <a href="autoclass-progress.html"><b>Current Work</a></b> <BR>
<IMG SRC = "/img/icons/purpleball.gif" ALT=""> <a href="autoclass-progress.html#future"><b>Future Work</a></b> <BR>
<IMG SRC = "/img/icons/purpleball.gif" ALT=""> <a href="autoclass-refs.html"><b>References</a></b> (and gif files)
<P>
<IMG SRC = "/img/icons/purpleball.gif" ALT=""><A HREF="autoclass-program.html">
<B>AutoClass III</B></A> -- An unsupervised Bayesian classification program in Lisp <BR>
<IMG SRC = "/img/icons/purpleball.gif" ALT=""><A HREF="autoclass-x-program.html">
<B>AutoClass X</B></A> -- An experimental extension of AutoClass III in Lisp <BR>
<IMG SRC = "/img/icons/purpleball.gif" ALT=""><A HREF="autoclass-c-program.html">
<B>AutoClass C</B></A> -- A public domain version of AutoClass III in C <BR>
 <p>
<A HREF="../index.html">
<IMG ALIGN=middle SRC="../images/rev-bayes-small.gif" alt="Drawing of Rev. Bayes"></A>
Bayesian Learning Group
</UL>

<h3><IMG SRC = "/img/icons/color-squares/purple.gif" ALT="">Introduction</h3>
In previous years, the Bayes group at Ames Research Center developed the basic
theory and associated algorithms for various kinds of general data analysis
techniques.  Our earliest efforts were applied to the problem of automatic
classification of data.  We implemented this theory in the Autoclass series
of programs.  AutoClass takes a database of cases described by a combination
of real and discrete valued attributes, and automatically finds the natural
classes in that data.  It does not need to be told how many classes are
present or what they look like -- it extracts this information from the data
itself.  The classes are described probabilistically, so that an object can
have partial membership in the different classes, and the class definitions
can overlap.  AutoClass generates reports on the classes it has found at the
end of its search.  AutoClass has been used and tested on many data sets, both
within NASA and by industry, academia and other agencies.  These applications
typically find surprising classifications that show patterns in the data
unknown to the user.  Examples include: discovery of new classes of infra-red
stars in the IRAS Low Resolution Spectral catalogue (see figure below; and
see <A HREF="http://www.ipac.caltech.edu/ipac/projects.html">here</A> and
<A HREF="http://nssdc.gsfc.nasa.gov/cd-rom/set_bd/iras.html">here</A> for more
information), new classes of airports in a database of all USA airports,
discovery of classes of proteins, introns and other patterns in DNA/protein
sequence data, and others. <P>

<IMG SRC = "/img/icons/rainbowline.gif" ALT="">

<H2>AutoClass Discovery in the IRAS Star Atlas</H2>

<IMG SRC="../images/IRAS-top.gif" alt="spectral values of the two groups of stars">

<DL>From subtle differences between their infrared spectra, two subgroups of stars were distinguished, where
previously no difference was suspected.</DL>

<IMG SRC="../images/IRAS-bottom-small.gif" alt="color-coded positions of stars on map of galaxy">

<DL>The difference is confirmed by looking at their positions on this map of the galaxy.</DL>
<P>

<IMG SRC = "/img/icons/rainbowline.gif" ALT="">
<P>
<A HREF="#Contents"><B>=- [ Return to AutoClass Project Contents ] -=</B></A>
<P>
Last updated August 06, 2002
</BODY>
</HTML>

--------------080602030900010502080501--


