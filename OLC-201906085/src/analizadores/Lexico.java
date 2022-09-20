package analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NOT_ACCEPT,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"32:9,31,30,32:2,31,32:18,38,32,35,36,32:3,40,18,19,24,22,32,23,37,25,34:10," +
"32,17,32:2,26,32:2,2,28,27,14,6,15,10,33,9,33:2,12,1,7,4,33:2,5,13,16,11,29" +
",33:2,3,33,32:4,8,32,2,28,27,14,6,15,10,33,9,33:2,12,1,7,4,33:2,5,13,16,11," +
"29,33:2,3,33,20,39,21,32:51,33,32:17,33,32:31,33,32:7988,33,32:57319,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,157,
"0,1,2,1:6,3,1,4,1:2,5,6,7,1,8,9,10,11,7:2,1,7,12,13,7:14,14,15,16,17,1,17,1" +
"8,10,16,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,4" +
"1,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,6" +
"6,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,9" +
"1,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,1" +
"12,113,7,114,115,116,117,118,119,120,121,122,123")[0];

	private int yy_nxt[][] = unpackFromString(124,41,
"1,2,146:2,95,146,151,152,146,153,146:3,43,154,74,96,3,4,5,6,7,8,9,10,11,12," +
"97,155,146,13,14,12,146,15,44,49,12,14,12,52,-1:42,146,98,146:3,99,146:10,-" +
"1:10,146:3,-1:3,146:2,-1:32,17,-1:38,42,18,-1:46,14,-1:6,14,-1:36,15,-1:2,4" +
"7,-1:4,146:16,-1:10,146:3,-1:3,146:2,-1:7,18:29,-1,18:10,-1:35,50,-1:39,20," +
"-1:7,146:7,83,146:8,-1:10,146:3,-1:3,146:2,-1:7,146:7,130,146:8,-1:10,146:3" +
",-1:3,146:2,-1:7,146:7,156,146:8,-1:10,146:3,-1:3,146:2,-1:7,42:23,57,42:16" +
",-1,146:8,16,146:7,-1:10,146:3,-1:3,146:2,-1:7,50:34,19,50:5,-1:34,45,-1:3," +
"45:2,-1:2,146:6,21,146:9,-1:10,146:3,-1:3,146:2,-1:7,146:8,22,146:7,-1:10,1" +
"46:3,-1:3,146:2,-1:7,53:39,55,-1:40,46,-1,146:5,23,146:10,-1:10,146:3,-1:3," +
"146:2,-1:46,53,-1,146:3,25,146:12,-1:10,146:3,-1:3,146:2,-1:7,42:23,57,24,4" +
"2:15,-1,146:4,26,146:11,-1:10,146:3,-1:3,146:2,-1:7,146:4,27,146:11,-1:10,1" +
"46:3,-1:3,146:2,-1:7,146:3,28,146:12,-1:10,146:3,-1:3,146:2,-1:7,146:3,29,1" +
"46:12,-1:10,146:3,-1:3,146:2,-1:7,146:8,30,146:7,-1:10,146:3,-1:3,146:2,-1:" +
"7,146,31,146:14,-1:10,146:3,-1:3,146:2,-1:7,146:6,32,146:9,-1:10,146:3,-1:3" +
",146:2,-1:7,146:12,33,146:3,-1:10,146:3,-1:3,146:2,-1:7,146:11,34,146:4,-1:" +
"10,146:3,-1:3,146:2,-1:7,146:4,35,146:11,-1:10,146:3,-1:3,146:2,-1:7,146:4," +
"36,146:11,-1:10,146:3,-1:3,146:2,-1:7,146:4,37,146:11,-1:10,146:3,-1:3,146:" +
"2,-1:7,146:11,38,146:4,-1:10,146:3,-1:3,146:2,-1:7,146:11,39,146:4,-1:10,14" +
"6:3,-1:3,146:2,-1:7,146:5,40,146:10,-1:10,146:3,-1:3,146:2,-1:7,146:3,41,14" +
"6:12,-1:10,146:3,-1:3,146:2,-1:7,146,104,146:6,48,146:7,-1:10,146:3,-1:3,14" +
"6:2,-1:7,146:12,51,146:3,-1:10,146:3,-1:3,146:2,-1:7,146:10,54,146:5,-1:10," +
"146:3,-1:3,146:2,-1:7,56,146:5,114,146:9,-1:10,146:3,-1:3,146:2,-1:7,146:3," +
"58,146:12,-1:10,146:3,-1:3,146:2,-1:7,146:3,59,146:12,-1:10,146:3,-1:3,146:" +
"2,-1:7,146:12,54,146:3,-1:10,146:3,-1:3,146:2,-1:7,146:4,60,146:11,-1:10,14" +
"6:3,-1:3,146:2,-1:7,146:8,61,146:7,-1:10,146:3,-1:3,146:2,-1:7,146:12,62,14" +
"6:3,-1:10,146:3,-1:3,146:2,-1:7,146:6,63,146:9,-1:10,146:3,-1:3,146:2,-1:7," +
"146,64,146:14,-1:10,146:3,-1:3,146:2,-1:7,146:5,65,146:10,-1:10,146:3,-1:3," +
"146:2,-1:7,146,66,146:14,-1:10,146:3,-1:3,146:2,-1:7,146,67,146:14,-1:10,14" +
"6:3,-1:3,146:2,-1:7,146:5,68,146:10,-1:10,146:3,-1:3,146:2,-1:7,146:3,69,14" +
"6:12,-1:10,146:3,-1:3,146:2,-1:7,146,70,146:14,-1:10,146:3,-1:3,146:2,-1:7," +
"146,71,146:14,-1:10,146:3,-1:3,146:2,-1:7,146:15,72,-1:10,146:3,-1:3,146:2," +
"-1:7,146:8,73,146:7,-1:10,146:3,-1:3,146:2,-1:7,146:7,75,146:8,-1:10,146:3," +
"-1:3,146:2,-1:7,146:4,76,146:11,-1:10,146:3,-1:3,146:2,-1:7,146,105,146,77," +
"146:12,-1:10,146:3,-1:3,146:2,-1:7,146:2,78,146:13,-1:10,146:3,-1:3,146:2,-" +
"1:7,146:6,79,146:9,-1:10,146:3,-1:3,146:2,-1:7,146:15,148,-1:10,146:3,-1:3," +
"146:2,-1:7,146:7,107,146:8,-1:10,146:3,-1:3,146:2,-1:7,108,146:15,-1:10,146" +
":3,-1:3,146:2,-1:7,146:8,109,110,146:6,-1:10,146:3,-1:3,146:2,-1:7,146:11,8" +
"0,146:4,-1:10,146:3,-1:3,146:2,-1:7,146:4,112,146:8,113,146:2,-1:10,146:3,-" +
"1:3,146:2,-1:7,146:3,149,146:12,-1:10,146:3,-1:3,146:2,-1:7,146:8,116,146:4" +
",117,146:2,-1:10,146:3,-1:3,146:2,-1:7,146:5,81,146:10,-1:10,146:3,-1:3,146" +
":2,-1:7,146:16,-1:10,82,146:2,-1:3,146:2,-1:7,146:4,118,146:11,-1:10,146:3," +
"-1:3,146:2,-1:7,146:11,119,146:4,-1:10,146:3,-1:3,146:2,-1:7,146,120,146:14" +
",-1:10,146:3,-1:3,146:2,-1:7,146:5,84,146:10,-1:10,146:3,-1:3,146:2,-1:7,14" +
"6:7,121,146:8,-1:10,146:3,-1:3,146:2,-1:7,146:6,123,146:9,-1:10,146:3,-1:3," +
"146:2,-1:7,146:9,124,146:6,-1:10,146:3,-1:3,146:2,-1:7,146:8,125,146:7,-1:1" +
"0,146:3,-1:3,146:2,-1:7,146:5,126,146:10,-1:10,146:3,-1:3,146:2,-1:7,146:3," +
"127,146:12,-1:10,146:3,-1:3,146:2,-1:7,146:16,-1:10,128,146:2,-1:3,146:2,-1" +
":7,146:16,-1:10,146:2,129,-1:3,146:2,-1:7,146:5,85,146:10,-1:10,146:3,-1:3," +
"146:2,-1:7,146:16,-1:10,86,146:2,-1:3,146:2,-1:7,146:10,87,146:5,-1:10,146:" +
"3,-1:3,146:2,-1:7,146:14,131,146,-1:10,146:3,-1:3,146:2,-1:7,146:12,88,146:" +
"3,-1:10,146:3,-1:3,146:2,-1:7,146:7,132,146:8,-1:10,146:3,-1:3,146:2,-1:7,1" +
"46:15,89,-1:10,146:3,-1:3,146:2,-1:7,146,133,146:14,-1:10,146:3,-1:3,146:2," +
"-1:7,146:8,134,146:7,-1:10,146:3,-1:3,146:2,-1:7,146:5,135,146:10,-1:10,146" +
":3,-1:3,146:2,-1:7,146:16,-1:10,136,146:2,-1:3,146:2,-1:7,146:11,90,146:4,-" +
"1:10,146:3,-1:3,146:2,-1:7,146:9,137,146:6,-1:10,146:3,-1:3,146:2,-1:7,146:" +
"4,139,146:11,-1:10,146:3,-1:3,146:2,-1:7,146:3,140,146:12,-1:10,146:3,-1:3," +
"146:2,-1:7,146:10,91,146:5,-1:10,146:3,-1:3,146:2,-1:7,146:10,92,146:5,-1:1" +
"0,146:3,-1:3,146:2,-1:7,146:5,141,146:10,-1:10,146:3,-1:3,146:2,-1:7,146:6," +
"142,146:9,-1:10,146:3,-1:3,146:2,-1:7,146:6,93,146:9,-1:10,146:3,-1:3,146:2" +
",-1:7,146:15,143,-1:10,146:3,-1:3,146:2,-1:7,146:4,144,146:11,-1:10,146:3,-" +
"1:3,146:2,-1:7,146,145,146:14,-1:10,146:3,-1:3,146:2,-1:7,146:4,94,146:11,-" +
"1:10,146:3,-1:3,146:2,-1:7,146:7,111,146:8,-1:10,146:3,-1:3,146:2,-1:7,146:" +
"3,115,146:12,-1:10,146:3,-1:3,146:2,-1:7,146:11,122,146:4,-1:10,146:3,-1:3," +
"146:2,-1:7,146:9,138,146:6,-1:10,146:3,-1:3,146:2,-1:7,146:6,100,146:5,101," +
"146:3,-1:10,146:3,-1:3,146:2,-1:7,146:10,102,146:5,-1:10,146:3,-1:3,146:2,-" +
"1:7,146:6,103,146:9,-1:10,146:3,-1:3,146:2,-1:7,146:5,147,146:10,-1:10,146:" +
"3,-1:3,146:2,-1:7,146:3,106,146:12,-1:10,146:3,-1:3,146:2,-1:7,146:8,150,14" +
"6:7,-1:10,146:3,-1:3,146:2,-1:6");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -3:
						break;
					case 3:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.PARIZQ,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.PARDER,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.LLAVIZQ,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.LLAVDER,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.MENOS,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -13:
						break;
					case 13:
						{yychar=1;}
					case -14:
						break;
					case 14:
						{}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.T_FLOAT,yyline,yychar,yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.T_SI,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.T_ASIG,yyline,yychar, yytext());}
					case -18:
						break;
					case 18:
						{}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.T_CADENA,yyline,yychar,yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.T_CHAR,yyline,yychar,yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.T_FIN,yyline,yychar, yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.T_O_SI,yyline,yychar, yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.T_VALOR_BOOL,yyline,yychar,yytext());}
					case -24:
						break;
					case 24:
						{}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.T_COMO,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.MAYQUE,yyline,yychar, yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.MENQUE,yyline,yychar, yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.T_INT,yyline,yychar, yytext());}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.T_INICIO,yyline,yychar, yytext());}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.T_FIN_SI,yyline,yychar, yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.T_STRING,yyline,yychar, yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.T_BOOLEAN,yyline,yychar, yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.T_ENTONCES,yyline,yychar, yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.T_IGUAL,yyline,yychar, yytext());}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.T_INGRESAR,yyline,yychar,yytext());}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.T_CARACTER,yyline,yychar, yytext());}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.T_CON_VALOR,yyline,yychar, yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.T_MAYIG,yyline,yychar, yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.T_MNIG,yyline,yychar, yytext());}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.T_DIFE,yyline,yychar, yytext());}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.T_CONTRARIO,yyline,yychar, yytext());}
					case -42:
						break;
					case 43:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -43:
						break;
					case 44:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -44:
						break;
					case 45:
						{return new Symbol(sym.T_FLOAT,yyline,yychar,yytext());}
					case -45:
						break;
					case 46:
						{return new Symbol(sym.T_CHAR,yyline,yychar,yytext());}
					case -46:
						break;
					case 48:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -47:
						break;
					case 49:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -48:
						break;
					case 51:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -49:
						break;
					case 52:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -50:
						break;
					case 54:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -51:
						break;
					case 56:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -52:
						break;
					case 58:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -53:
						break;
					case 59:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -54:
						break;
					case 60:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -55:
						break;
					case 61:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -56:
						break;
					case 62:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -57:
						break;
					case 63:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -58:
						break;
					case 64:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -59:
						break;
					case 65:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -60:
						break;
					case 66:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -61:
						break;
					case 67:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -62:
						break;
					case 68:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -63:
						break;
					case 69:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -64:
						break;
					case 70:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -65:
						break;
					case 71:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -66:
						break;
					case 72:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -67:
						break;
					case 73:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -68:
						break;
					case 74:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -69:
						break;
					case 75:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -70:
						break;
					case 76:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -71:
						break;
					case 77:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -72:
						break;
					case 78:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -73:
						break;
					case 79:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -74:
						break;
					case 80:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -75:
						break;
					case 81:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -76:
						break;
					case 82:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -77:
						break;
					case 83:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -78:
						break;
					case 84:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -79:
						break;
					case 85:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -80:
						break;
					case 86:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -81:
						break;
					case 87:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -82:
						break;
					case 88:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -83:
						break;
					case 89:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -84:
						break;
					case 90:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -85:
						break;
					case 91:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -86:
						break;
					case 92:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -87:
						break;
					case 93:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -88:
						break;
					case 94:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -89:
						break;
					case 95:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -90:
						break;
					case 96:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -91:
						break;
					case 97:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -92:
						break;
					case 98:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -93:
						break;
					case 99:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -94:
						break;
					case 100:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -95:
						break;
					case 101:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -96:
						break;
					case 102:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -97:
						break;
					case 103:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -98:
						break;
					case 104:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -99:
						break;
					case 105:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -100:
						break;
					case 106:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -101:
						break;
					case 107:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -102:
						break;
					case 108:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -103:
						break;
					case 109:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -104:
						break;
					case 110:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -105:
						break;
					case 111:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -106:
						break;
					case 112:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -107:
						break;
					case 113:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -108:
						break;
					case 114:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -109:
						break;
					case 115:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -110:
						break;
					case 116:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -111:
						break;
					case 117:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -112:
						break;
					case 118:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -113:
						break;
					case 119:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -114:
						break;
					case 120:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -115:
						break;
					case 121:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -116:
						break;
					case 122:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -117:
						break;
					case 123:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -118:
						break;
					case 124:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -119:
						break;
					case 125:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -120:
						break;
					case 126:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -121:
						break;
					case 127:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -122:
						break;
					case 128:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -123:
						break;
					case 129:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -124:
						break;
					case 130:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -125:
						break;
					case 131:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -126:
						break;
					case 132:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -127:
						break;
					case 133:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -128:
						break;
					case 134:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -129:
						break;
					case 135:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -130:
						break;
					case 136:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -131:
						break;
					case 137:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -132:
						break;
					case 138:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -133:
						break;
					case 139:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -134:
						break;
					case 140:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -135:
						break;
					case 141:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -136:
						break;
					case 142:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -137:
						break;
					case 143:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -138:
						break;
					case 144:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -139:
						break;
					case 145:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -140:
						break;
					case 146:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -141:
						break;
					case 147:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -142:
						break;
					case 148:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -143:
						break;
					case 149:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -144:
						break;
					case 150:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -145:
						break;
					case 151:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -146:
						break;
					case 152:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -147:
						break;
					case 153:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -148:
						break;
					case 154:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -149:
						break;
					case 155:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -150:
						break;
					case 156:
						{return new Symbol(sym.T_ID,yyline,yychar,yytext());}
					case -151:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
