package com.ebodoo.raz.utils;

import java.util.ArrayList;
import java.util.Arrays;

import android.util.Log;

public class CommonYuyin {

	
	
	public Grade getGrade(float score) {
		if (score > 2.5f) {
			return Grade.Perfect;
		} else if (score > 1.9f) {
			return Grade.Good;
		} else if (score > 1.3f) {
			return Grade.Fair;
		} else if (score > 0.6f) {
			return Grade.JustSoSo;
		} else
			return Grade.TryAgain;
	}

	public enum Grade {
		Perfect, Good, Fair, JustSoSo, TryAgain,

	};
	/*
	 * @param 	targetString sentence used to be recognized
	 * @param 	result 		voice recognition result
	 * @return 	the percentage of recognized words
	 */
//	public float getScore(String targetString, Hypothesis result){
//		ArrayList<String> recog_Result;
//		recog_Result = new ArrayList<String>();
//		String words_tmp = replaceNonCharacterWithSpace(targetString.toLowerCase());
//		recog_Result.addAll(Arrays.asList(result.getHypstr().split(" ")));
//		String[] ref_Words = words_tmp.split(" ");
//		ArrayList<String> not_recog_words = new ArrayList<String>(
//				Arrays.asList(ref_Words));
//		
//		for (String single_word : recog_Result) {
//			for (String ref_single_word : ref_Words) {
//				if (single_word.equalsIgnoreCase(ref_single_word)) {
//					not_recog_words.remove(ref_single_word);
//				}
//			}
//		}
//		
//		for (int j = 0; j < not_recog_words.size();j++) {
//			String not_recog_string = not_recog_words.get(j);
//			if(hasNonCharacter(not_recog_string)){
//				String test = removeNonCharacter(not_recog_string);
//				for (String resultstr:recog_Result){
//					if(resultstr.equalsIgnoreCase(test)){
//						not_recog_words.remove(not_recog_string);
//						j --;
//						break;
//					}
//				}
//			}
//		}
//		return (ref_Words.length-not_recog_words.size())/ref_Words.length*1.0f;
//	}
	
	
//	public static float getScoreP(String mphoneme, Hypothesis result,Phonics mPhonics) {
//		String phonics = mPhonics.getPhonicsFromPhome(mphoneme);
//		String results = result.getHypstr();
//		String[] groups = results.split(" ");
//		for (String group : groups) {
//			String[] pair = group.split(",");
//			if (pair != null) {
//				if (phonics.equalsIgnoreCase(pair[0])) {
//					return Float.valueOf(pair[1]);
//				}
//			}
//		}
//		return 0.0f;
//	}
	
	
	public String replaceNonCharacterWithSpace(String orgstr) {
		char[] newchar = orgstr.toCharArray();
		int i = 0;
		int count = newchar.length;

		for (; i < count; i++) {
			if (newchar[i] == ',' || newchar[i] == '?' || newchar[i] == '!'
					|| newchar[i] == '.') {
				
				newchar[i] = ' ';
			}
		}
		return String.valueOf(newchar);

	}
	public String replaceString(String targetstr, String str, int start) {
		int length = str.length();
		char[] chars = targetstr.toCharArray();
		for (int i = 0; i < length; i++) {
			chars[start + i] = str.charAt(i);
		}
		return String.valueOf(chars);
	}

	public String buildStringWithSpace(int length, char fillchar) {
		StringBuilder strbld = new StringBuilder();
		for (int i = 0; i < length; i++) {
			strbld.append(fillchar);
		}
		return strbld.toString();
	}
	
	public boolean hasNonCharacter(String orig){
		char[] chars = orig.toCharArray();
		for(char a:chars){
			if(  a > 96 && a < 123){
				continue;
			} else {
				return true;
			}
		}
		return false;
	}
	public String removeNonCharacter(String orig){
		Log.d("Kyle","remove non character: "+orig );
		char[] chars = orig.toCharArray();
//		char[] result = new char[ orig.length()];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< chars.length; i++){
			char a = chars[i];
			if(  a > 96 && a < 123 ){
				Log.d("Kyle","add char: "+a);
				sb.append(a);
			}
		}
		return sb.toString();
	}

	
	
	
	
	
	
	
	
	
}
