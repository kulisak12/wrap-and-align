/*
 * MIT License
 * Copyright (c) 2017 Gymnazium Nad Aleji
 * Copyright (c) 2017 Vojtech Horky
 * Copyright (c) 2019 David Klement
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cz.alisma.alej.text.wrapping;

import java.util.Scanner;

public class WrapAndAlign {
    private static final int MAX_WIDTH = 50;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ParagraphDetector pd = new ParagraphDetector(input);
        int width = getWidth(args);
        Aligner aligner = buildAligner(args, width);

        while (pd.hasNextParagraph()) {
            Paragraph para = pd.nextParagraph();
            LinePrinter line = new LinePrinter(System.out, width, aligner);
            while (para.hasNextWord()) {
                String word = para.nextWord();
                line.addWord(word);
            }
            line.flush();
            System.out.println();
        }
    }
    
    private static int getWidth(String[] args) {
    	// find width switch
    	for (int i = 0; i < args.length; i++) {
    		String arg = args[i];
    		
    		if (arg.startsWith("--width=")) {
    			return Integer.parseInt(arg.substring(arg.indexOf('=') + 1));
    		}
    		else if (arg.equals("-w")) {
    			return Integer.parseInt(args[i + 1]);
    		}
    	}
    	
    	// width not set
    	return MAX_WIDTH;
    }
    
    private static Aligner buildAligner(String[] args, int width) {
    	Aligner aligner = new LeftAligner(); // default
    	
    	// set aligner type
    	for (int i = 0; i < args.length; i++) {
    		String arg = args[i];
    		
    		if (arg.equals("--right")) {
    			aligner = new RightAligner(width);
    		}
    		else if (arg.equals("--center") || arg.equals("--centre")) {
    			aligner = new CenterAligner(width);
    		}
    		else if (arg.equals("--justify")) {
    			aligner = new JustifyAligner(width);
    		}
    	}
    	
    	return aligner;
    }
}
