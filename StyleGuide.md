# IE:HW Style Guide

## 1. Source file basics
###		2.1 File name
The source file name consists of the case-sensitive name of the top-level class it contains 
(of which there is exactly one), plus the .java extension.
## 2. Source file structure
A source file consists of, __in order__:
1. Package Statement
2. Import statements
3. Exactly one top-level class
Exactly __one blank line__ separates each section that is present
###		2.1 Import statements: No wildcard imports
Wildcard imports __are not used__ .
###		2.2 Class declaration
###			2.2.1 Exactly one top-level class declaration
Each top-level class resides in a source file of its own
###			2.2.2 Overloads: Never split
When a class has multiple constructors , or simple methods with the same name, 
these appear sequentially, with no other code in between.
##	3. Formatting
###		3.1 Braces
###			3.1.1 Braces are not used where optional
Braces are not used with _if_ , _else_, _for_, _do_ and _while_ statements
when the body is empty or contains a single statement
###			3.1.2 Nonempty blocks
* Linebreak before the opening brace
* Linebreak after the opening brace
* Linebreak before the closing brace
* Linebreak after the closing brace, _only if_ that brace terminates a statement,
or terminates the body of a method, constructor or named class. For example
there is _no_ linebreak after the brace if it is followed by else.
Example:
```java
			public void MyClass
			{
				public void method()
				{
					if (condition()){
						try{
							something();
						}catch (ProblemException e)
						{
							recover();
					}else
					{
						lastThing();
					}
				}
			}
```
###		3.2 Block indentation: +4 spaces
Each time a new block or block-like construct is opened, the indent increases by four spaces.
When the block ends, the indent returns to the previous indent level. The indent level applies
to both code and comments throughout the block.
###		3.3 One statement per line
Each statement is followed by a linebreak
###		3.4 Column limit: 80
Code has a column limit of 80 characters, except in the cases of package and import statements.
###		3.5 Whitespace
###			3.5.1 Vertical Whitespace
A single blank line appears between different types of blocks: 
Variables definition, static methods, instance methods.
###			3.5.2 Horizontal whitespace
A single ASCII space appears when:
1. Separating any reserved word such asif, for or catch 
	from an open parenthesis on that line
2. Separating any reserved word, such as else or catch, 
	from a closing curly brace that precedes it on that line.
3. On both sides of any binary or ternary opeartor.
4. On both sides of the double slash ( // ) that begins and end-of-line comment.
5. Between the type and variable of a declaration
###		3.6 Specific constructs
###			3.6.1 Variable declarations
Every variable declaration declares only one variable: declarations such as _int a, b;_ are not used.
##	4. Naming
###		4.1. Rules common to all identifiers
Identifiers only use ASCII letters and digit, and, in a small number of cases noted below, underscores.
###		4.2 Rules by identifier type
###			4.2.1 Package names
Package names are all lowercase, with consecutive words simply concatenated together (no underscores).
###			4.2.2 Class names
Class names are written in **UpperCamelCase**. Test classes are named starting with the name of the
class they are testing, and ending with Test.
###			4.2.3 Method names
Method names are written in **lowerCamelCase**
###			4.2.4 Constant names
Constant names use CONSTANT_CASE : all uppercase letters, with words separated by underscores.
###			4.2.5 Non-constant field names
Non-constant field names (static or otherwise) are written in **lowerCamelCase**
###			4.2.6 Parameter names
Parameter names are written in **lowerCamelCase**
###			4.2.7 Local variable names
Local variable names are written in **lowerCamelCase**
###			4.2.8 Type variable names
Each type variable is named with a single capital letter, optionally followed by a single numeral.
				
@TODO Javadoc
				
		
