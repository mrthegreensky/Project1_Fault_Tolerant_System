# Project1_Fault_Tolerant_System
Explore the design & construction of fault-tolerant systems using Java and C.
2 separate programs were written: a data generator that creates random integer values and writes them to a file, and a sorting program that reads that file, sorts the values, and writes them to another file. 
The sorting program is fault-tolerant, with a primary sorting routine written in Java, and a backup sorting routine written in C. 
The C routine should be called as a native method from within Java.
There will also be a small chance of either the main or backup sorting routine failing on any memory access operation; these are probabilities that must be passed as commandline arguments to the sorting program.
