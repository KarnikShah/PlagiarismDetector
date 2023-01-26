# PlagiarismDetector

In order to determine whether a text file is plagiarised or not, a plagiarism detector looks at two text files and does binary classification, comparing how similar one text file is to the other and classifying the file as either plagiarised (1) or not (0). A plagiarism detector may be made in a variety of methods, however, we choose to employ the Longest Common Subsequence (LCS) algorithm. By comparing two files of text, this method would determine the longest subsequence that is shared by both. The degree of similarity between the two pieces of text would then be calculated using this subsequence.

# Features Explanation
First, we'll run our source code and read the contents of both files using the "readFileContent" command before saving them into a BufferedReader which is converted into string using stringBuilder for later usage. The text files' comments are initially removed as part of the plagiarism-detecting process. If comments are present, the "removeComments" or "removeInLineComments" function will eliminate all comments from the text files. Whitespaces must also be removed (using the function "removeWhiteSpace") in order to make it simpler and more effective for us to use the LCS approach to discover the common subsequence between the two files.

The LCS between the two texts has to be calculated next. To determine the longest common subsequence between two strings, the LCS method is a well-known technique. Because it uses dynamic programming, it works well for texts of a reasonable length. The LCS may be used to identify plagiarism after it has been computed. The LCS ratio may be calculated, which is a straightforward approach among many others. The LCS's length is simply divided by the length of the minimum of two texts' to arrive at this ratio. The command can be demonstrated as follows:
return (1.0 * getLCS(cleanStr1, cleanStr2)) / Math.min(cleanStr1.length, cleanStr2.length);

If the LCS ratio is large, there is likely to be a significant overlap between the two texts, which will result in an output of 1, indicating that both files include plagiarism; otherwise, the outcome will be 0. Here, we've chosen a 40% barrier for detection purposes, which means that if anything crosses it, we'll receive output as 1 (plagiarised) otherwise 0 (not plagiarised) as an output. Furthermore, it will also return 0 if any of the text files is empty. The function for this is written as:
int result = plagiarismDetector.get(input1, input2) > 0.40 ? 1 : 0;

# Runtime requirement
The code for the plagiarism detector can be executed using the "make FILE1=<path_to_file1> FILE2=<path_to_file2> run" command. 
A makefile is a unique file that contains details on the targets, libraries, and flow of the program. Another name for it is a description file. A list of targets and the sources they depend on are contained in the Makefile. In addition, libraries that support source code are included in the Makefile. Installing the make command-line tool is a must for running Makefile on Windows.
