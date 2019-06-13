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

import java.util.List;

/** Adds spaces so that each line has the same width (as in newspapers). */
public class JustifyAligner implements Aligner {
	private int finalLength;
	
	public JustifyAligner(int finalLength) {
		this.finalLength = finalLength;
	}
	
    @Override
    public String format(List<String> words) {
        StringBuilder result = new StringBuilder();
        
        int unmodifiedLength = 0;
        for (String w : words) {
            unmodifiedLength += w.length();
        }
        int spacesToAdd = finalLength - unmodifiedLength;
        int wordsLeft = words.size();
        
        boolean first = true;
        for (String w : words) {
            if (!first && wordsLeft > 1) {
            	int numSpaces = spacesToAdd / (wordsLeft - 1);
            	result.append(createSpaces(numSpaces));
            	spacesToAdd -= numSpaces;
            	wordsLeft--;
            } else {
                first = false;
            }
            result.append(w);
        }
        
        return result.toString();
    }
    
    private String createSpaces(int count) {
    	String result = "";
    	for (int i = 0; i < count; i++) {
    		result += " ";
    	}
    	return result;
    }

}
