package com.bl;

import java.util.ArrayList;
import java.util.List;

public class DiffChecker {

	public List<List<String>> diffCheckerMain(String X, String Y, boolean isCheckByCharSelected) {

		List<List<String>> diffs = new ArrayList<>();
		diffs.add(new ArrayList<>());
		diffs.add(new ArrayList<>());

		if (isCheckByCharSelected) {
			checkDiffByChar(X, Y, diffs);
		} else {
			checkDiffByWord(X, Y, diffs);
		}

		return diffs;
	}

	public static void checkDiffByChar(String x, String y, List<List<String>> diffs) {
		String X = x.trim();
		String Y = y.trim();
		int m = X == null ? Y.length() : X.length();
		int n = Y == null ? X.length() : Y.length();
		int[][] lookup = new int[m + 1][n + 1];

		// fill lookup table
		LCSLength(X, Y, X.length(), Y.length(), lookup);

		// find the difference
		diff(X, Y, X.length(), Y.length(), lookup, diffs);
		System.out.println();
		for (int i = 0; i < 2; i++) {
			System.out.println(diffs.get(i).toString());
		}
	}

	public static void checkDiffByWord(String x, String y, List<List<String>> diffs) {
		String[] X = x.split(" ");
		String[] Y = y.split(" ");
		int m = X.length;
		int n = Y.length;
		int[][] lookup = new int[m + 1][n + 1];
		LCSWordLength(X, Y, m, n, lookup);
		diffByWord(X, Y, m, n, lookup, diffs);
		System.out.println();
		for (int i = 0; i < 2; i++) {
			System.out.println(diffs.get(i).toString());
		}
	}

	private static void diffByWord(String[] X, String[] Y, int m, int n, int[][] lookup,
			List<List<String>> differences) {

		if (m > 0 && n > 0 && X[m - 1].equalsIgnoreCase(Y[n - 1])) {
			diffByWord(X, Y, m - 1, n - 1, lookup, differences);
			System.out.print(" " + X[m - 1]);
			differences.get(0).add(" " + X[m - 1]);
			differences.get(1).add(" " + Y[n - 1]);
		}

		// if the current character of `Y` is not present in `X`
		else if (n > 0 && (m == 0 || lookup[m][n - 1] >= lookup[m - 1][n])) {
			diffByWord(X, Y, m, n - 1, lookup, differences);
			System.out.print(" +" + Y[n - 1]);
			differences.get(0).add("-" + Y[n - 1]);
			differences.get(1).add("+" + Y[n - 1]);
		}

		// if the current character of `X` is not present in `Y`
		else if (m > 0 && (n == 0 || lookup[m][n - 1] < lookup[m - 1][n])) {
			diffByWord(X, Y, m - 1, n, lookup, differences);
			System.out.print(" -" + X[m - 1]);
			differences.get(0).add("+" + X[m - 1]);
			differences.get(1).add("-" + X[m - 1]);
		}
	}

	/**
	 * return LCS length when compared with words
	 * 
	 * @param x
	 * @param y
	 * @param m
	 * @param n
	 */
	private static void LCSWordLength(String[] X, String[] Y, int m, int n, int[][] lookup) {
		for (int i = 0; i <= m; i++) {
			lookup[i][0] = 0;
		}
		for (int j = 0; j <= n; j++) {
			lookup[0][j] = 0;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (X[i - 1].equalsIgnoreCase(Y[j - 1])) {
					lookup[i][j] = 1 + lookup[i - 1][j - 1];
				} else {
					lookup[i][j] = Math.max(lookup[i][j - 1], lookup[i - 1][j]);
				}
			}
		}
	}

	// Function to fill the lookup table by finding the length of LCS
	// of substring `X[0???m-1]` and `Y[0???n-1]`
	public static void LCSLength(String X, String Y, int m, int n, int[][] lookup) {
		// first column of the lookup table will be all 0
		for (int i = 0; i <= m; i++) {
			lookup[i][0] = 0;
		}

		// first row of the lookup table will be all 0
		for (int j = 0; j <= n; j++) {
			lookup[0][j] = 0;
		}

		// fill the lookup table in a bottom-up manner
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// if current character of `X` and `Y` matches
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					lookup[i][j] = lookup[i - 1][j - 1] + 1;
				}
				// otherwise, if the current character of `X` and `Y` don't match
				else {
					lookup[i][j] = Integer.max(lookup[i - 1][j], lookup[i][j - 1]);
				}
			}
		}
	}

	public static void diff(String X, String Y, int m, int n, int[][] lookup, List<List<String>> differences) {
		// if the last character of `X` and `Y` matches
		if (m > 0 && n > 0 && X.charAt(m - 1) == Y.charAt(n - 1)) {
			diff(X, Y, m - 1, n - 1, lookup, differences);
			System.out.print(" " + X.charAt(m - 1));
			differences.get(0).add(" " + X.charAt(m - 1));
			differences.get(1).add(" " + Y.charAt(n - 1));
		}

		// if the current character of `Y` is not present in `X`
		else if (n > 0 && (m == 0 || lookup[m][n - 1] >= lookup[m - 1][n])) {
			diff(X, Y, m, n - 1, lookup, differences);
			System.out.print(" +" + Y.charAt(n - 1));
			differences.get(0).add("-" + Y.charAt(n - 1));
			differences.get(1).add("+" + Y.charAt(n - 1));
		}

		// if the current character of `X` is not present in `Y`
		else if (m > 0 && (n == 0 || lookup[m][n - 1] < lookup[m - 1][n])) {
			diff(X, Y, m - 1, n, lookup, differences);
			System.out.print(" -" + X.charAt(m - 1));
			differences.get(0).add("+" + X.charAt(m - 1));
			differences.get(1).add("-" + X.charAt(m - 1));
		}
	}

}
