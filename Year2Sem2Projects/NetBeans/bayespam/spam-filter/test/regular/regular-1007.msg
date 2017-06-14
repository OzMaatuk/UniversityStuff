From L.Schomaker@ai.rug.nl Wed Nov 12 13:32:34 2003
Return-Path: <L.Schomaker@ai.rug.nl>
Received: from ai.rug.nl (psyX7.ai.rug.nl [129.125.139.207])
	by tcw2.ppsw.rug.nl (8.12.8/8.12.8) with ESMTP id hACCWZOj010943
	for <bulacu@ai.rug.nl>; Wed, 12 Nov 2003 13:32:35 +0100
Message-ID: <3FB22862.4020407@ai.rug.nl>
Date: Wed, 12 Nov 2003 13:32:34 +0100
From: Lambert Schomaker <L.Schomaker@ai.rug.nl>
User-Agent: Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.2.1) Gecko/20030225
X-Accept-Language: en-us, en
MIME-Version: 1.0
To: bulacu@ai.rug.nl
Subject: Suggested student project: Identification of Medieval Scribal Handwriting
Content-Type: multipart/mixed;
  boundary="------------000202070306010406060201"
X-ai-MailScanner-Information: Please contact helpdesk@ai.rug.nl for more information
X-ai-MailScanner: Found to be clean
X-ai-MailScanner-SpamCheck: not spam (whitelisted),
	SpamAssassin (score=-99.6, required 4, LINES_OF_YELLING 0.21,
	MAILTO_TO_SPAM_ADDR 0.69, SPAM_PHRASE_00_01 0.78, USER_AGENT -0.52,
	USER_AGENT_MOZILLA_UA -0.60, USER_IN_WHITELIST -100.00,
	X_ACCEPT_LANG -0.21)
Status: RO
X-Status: U
X-KMail-EncryptionState:  
X-KMail-SignatureState:  

This is a multi-part message in MIME format.
--------------000202070306010406060201
Content-Type: text/plain; charset=us-ascii; format=flowed
Content-Transfer-Encoding: 7bit

not so nice not to contact us about it...

<http://www.cl.cam.ac.uk/users/jgd1000/scribes.html>

--------------000202070306010406060201
Content-Type: text/html;
 name="www.cl.cam.ac.uk/users/jgd1000/scribes.html"
Content-Transfer-Encoding: 7bit
Content-Disposition: inline;
 filename="www.cl.cam.ac.uk/users/jgd1000/scribes.html"
Content-Base: "http://www.cl.cam.ac.uk/users/jgd1000/
	scribes.html"
Content-Location: "http://www.cl.cam.ac.uk/users/jgd1000/
	scribes.html"

<HTML> 
<TITLE>Suggested student project:  Identification of Medieval Scribal Handwriting
</TITLE>
<A HREF="http://www.cl.cam.ac.uk/">
<IMG SRC="http://www.cl.cam.ac.uk/UoCCL/img/UoCCL.gif" 
WIDTH=289 HEIGHT=94 BORDER=0 HSPACE=5 ALIGN="RIGHT"> </A>
<BR>
<B>
<BODY>
Suggested student project:
<H2>Identification of Medieval Scribal Handwriting</H2>
</B>
<P>
<IMG SRC="http://www.cl.cam.ac.uk/users/jgd1000/scribesintro.jpg"
ALT="(loading 25K)"
WIDTH=448 HEIGHT=203 BORDER=0 >
<BR>
<SMALL>
National Library of Wales, Peniarth 392D, f. 13<BR>
By permission of the National Library of Wales
</SMALL>
<BR>
<P>
PROJECT ORIGINATORS:   <A HREF="http://www.cl.cam.ac.uk/users/jgd1000/">
John Daugman</A> and 
<A HREF="http://www.umaine.edu/english/facultypages/!LRMWEBP.HTM">Linne Mooney</A> (lrm10@cam.ac.uk)<BR>
<P>
BACKGROUND OF PROJECT<BR>
<P>
Five hundred years ago in Europe a major transition in the written 
dissemination of ideas took place, brought about by the invention
of printing (and in England from its introduction by William Caxton in 1476).
Historians of the book ("codicologists") are seeking to recover the trends 
in the dissemination of texts immediately preceding and after this revolution
by identifying the scribes who copied medieval manuscripts in England at the end 
of the manuscript era (1375-1525).  They are interested in questions such as:
what texts did the scribes copy most;  at what rate did
they copy them, or how many manuscripts can be attributed to a single hand;
is there evidence of a movement toward mass production and commercial sale of
manuscripts immediately before the introduction of print in 1476? 
<P>
The identification of same-scribe specimens of medieval English handwriting 
currently depends upon the expert eyes of specialists in dating handwriting
("palaeographers") because English scribes rarely signed their work.  But
thousands of manuscripts survive from the fifteenth century and there is as yet
no known computational method for classifying or categorizing them by handwriting 
which could assist those trying to identify new specimens.
Professor Linne Mooney, an expert codicologist-palaeographer visiting Cambridge 
this year would like to enlist us to devise algorithms for classifying manuscripts 
by scribe on the basis of their handwriting.  
<P>
DETAILS OF PROJECT<BR>
<P>
Several samples of scribal handwriting by each of 10 scribes will be provided on a CD 
to students selecting this project, in JPEG images such as seen here, classified already
by scribal identity.  Transcriptions of these samples will also be provided as text
files so that the "ground truth" about each character will be available.  Students
adopting this project will devise feature extraction algorithms, learning algorithms, 
and statistical classification algorithms, that segregate these 10 scribes as well as
possible in accordance with their known identities.  In the Spring of 2004 a further
set of test samples of scribal handwriting will be provided:  some by the original 10
scribes and some by others, all without their identities given.  Students will be
expected to test and to document the performance of their algorithms against this 
blind test set, and to analyse their algorithms' results, in their dissertations.
<P>
The heart of this project is finding features and creating mathematical representations
of them that optimally separate the different scribal classes.  Almost the entire
science of pattern recognition is spawned by the question of a single relationship:  
the comparative
magnitudes of within-class variability and between-class variability.  Patterns can
be reliably recognised, or classified, only if the representations of them express
between-class variability that is larger than their within-class variability. 
(In terms of cluster analysis, this means that the spacings between clusters must
be larger than the diameters of the clusters.)  In the present context, this principle
requires that the variability amongst the hands of different scribes, as represented
in the computed set of features, should be greater than the variability in the 
representation of the hand of any given scribe
over time or across manuscripts.  How can this be accomplished?
<P>
Approaches to handwriting classification may be divided broadly into extraction
of "global" versus "local" features.  Globally, specimens of handwriting by 
medieval scribes differ in terms of the angle of slant, the size of writing, height
of lower-case letters in relation to ruled lines (how much white space between
lines), height of ascenders (of letters 'b', 'f', 'h', 'l', and medieval long 's')
or depth of descenders (of letters like 'g', 'p', 'q', and 'y') in relation to the 
normal height of lower-case letters or ruled lines.  For example, consider the
following sample by a scribe who may or may not be the same as the scribe who
wrote the sample above:<BR>
<IMG SRC="http://www.cl.cam.ac.uk/users/jgd1000/tcc.jpg"
ALT="(loading 25K)"
WIDTH=358 HEIGHT=242 BORDER=0 >
<BR>
<SMALL>
Trinity College, Cambridge, B.15.17, f. 125v<BR>
By permission of the Master and Fellows of Trinity College, Cambridge
</SMALL>
<BR>
<P> Locally, analysis involves 
individual letter forms, of which a scribe might have more than one in his writing
repertoire (one for more formal writing, one for more casual writing, for instance).
Palaeographers have identified a few letter-forms that vary most in the handwriting
of medieval English scribes and that might therefore be best for determining
between-class variability, and hence may be the best determiners for isolating
same-scribe specimens of handwriting:  'a', 'd', 'g', 'h', 'p', 'r', 's', 'w', and 
medieval English representations of 'th' and ampersand.  Here are some examples
of the letter 'g' by the same scribe: <BR>
<IMG SRC="http://www.cl.cam.ac.uk/users/jgd1000/heng-g4.jpg"
WIDTH=21 HEIGHT=37 BORDER=0>
<IMG SRC="http://www.cl.cam.ac.uk/users/jgd1000/heng-g2.jpg"
WIDTH=16 HEIGHT=29 BORDER=0>
<IMG SRC="http://www.cl.cam.ac.uk/users/jgd1000/heng-g3.jpg"
WIDTH=15 HEIGHT=34 BORDER=0>
<IMG SRC="http://www.cl.cam.ac.uk/users/jgd1000/heng-g1.jpg"
WIDTH=23 HEIGHT=33 BORDER=0>  <SMALL> (Peniarth, <CITE>op cit.</CITE>) </SMALL>
<BR>
But you may find that 
other letter-forms are more distinctive from a computational point of view,
or that only a few of these will suffice for finding distinctions and making
classifications.  Whatever types of discriminating features are selected,
distance metrics are then constructed that gauge the similarity or
dissimilarity of the script samples and plot their relationships in a
feature space.  It is often useful to represent the data not in terms of
raw features but in terms of more abstract descriptions that are
computed by mathematical operations such as Principal Components Analysis,
or the Karhunen-Loeve Transform.  Probability theory and statistical models
then drive the classification process through a variety of possible
data fusion, decision, and inference techniques.
<BR>
<P>
<P>
REFERENCES<BR>
<UL>
<LI>Duda R O, Hart P E, and Stork D G (2001) <CITE>Pattern Classification</CITE>,
2nd ed. (New York:  Wiley). 
<LI> Schomaker L, Bulacu M, and van Erp M (2003) "Sparse-parametric writer
identification using heterogeneous feature groups," Proc. of IEEE International
Conference on Image Processing 2003.
<LI> Bulacu M and Schomaker L (2003) "Writer style from oriented edge fragments,"
Proc. of CAIP, LNCS 2756, pp. 460-469.
<LI> (related work by Prof. Lambert Schomaker and his group at the
University of Groningen is accessible via this
<A HREF="http://www.ai.rug.nl/~lambert/">link</A>.)
<LI> Zhu Y, Tan T, and Wang Y (2001) "Font recognition based on global texture
analysis," <CITE> IEEE Trans. Pattern Analysis and Machine Intelligence</CITE>,
23(10), pp. 1192-1200.
<LI> Website on handwriting recognition by Srihari et al. at SUNY-Buffalo:
<A HREF="http://www.cedar.buffalo.edu/papers/publications.html">
http://www.cedar.buffalo.edu/papers/publications.html</A>
<LI> A related project idea:
<A HREF="http://www.cl.cam.ac.uk/~mgk25/only-cam/project-ideas-2003.html#genizah">
http://www.cl.cam.ac.uk/~mgk25/only-cam/project-ideas-2003.html#genizah</A>
<LI> Arazi B (1977) "Handwriting identification by means of run-length measurements,"
<CITE> IEEE Trans. Syst., Man and Cybernetics</CITE>, SMC-7(12), pp. 878-881.
<LI> Maarse F, Schomaker L, and Teulings H-L (1988) "Automatic identification of
writers." In <CITE>Human-Computer Interaction:  Psychonomic Aspects</CITE>
pp. 353-360 (New York:  Springer).
<LI> Saks and Risenger (1996) "Science and non-science in the courts: 
Daubert meets handwriting identification expertise,"
<CITE>Iowa Law Review</CITE>, available 
<A HREF="http://www.law-forensic.com/handwriting_2.htm">here</A> 
</UL>
<HR SIZE=2>
<BR>
</BODY>
</HTML>


--------------000202070306010406060201--

