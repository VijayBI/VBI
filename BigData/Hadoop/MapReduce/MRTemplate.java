package com.hpe.hadoop.mapreduce;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MRTemplate 
{  //Main class is opened
	//Develop Mapper class for implementing mapper side business logic
	public static class MyMapper extends Mapper<MKEYIN,MVALIN,MKEYOUT,MVALOUT>
	{//Mapper class is opened
		public void setup(Context context) throws IOException, InterruptedException
		{
			//Initialise all the variables which are used in Mapper method
		}
		public void map(MKEYIN Key, MVALIN val, Context context) throws IOException, InterruptedException
		{
			//Write mapper side business logic i.e. transformations
		}
		public void cleanup(Context context) throws IOException, InterruptedException
		{
			//close all the resources and connections which are used in Mapper method
		}
		public void run(Context context) throws IOException, InterruptedException
		{
			//Calls the Mpper task life cycle 
		}
	} //Mapper class closed
	
	//Develop Reducer class for implementing reducer side business logic
	public static class MyReducer extends Reducer<RKEYIN, RVALIN, RKEYOUT, RVALOUT>
	{//Reducer class opened
		public void setup(Context context) throws IOException, InterruptedException
		{
			//Initialise all the variables which are used in Reducer method
		}
		public void reduce(RKEYIN Key, Iterable<VALUEIN> values, Context context) throws IOException, InterruptedException
		{
			//Write reducer side business logic...
		}
		public void cleanup(Context context) throws IOException, InterruptedException
		{
			//close all the resources and connections which are used in Reducer method
		}
		public void run(Context context) throws IOException, InterruptedException
		{
			//Calls the Reducer task life cycle 
		}	
	}//Reducer class closed
	
	//Develop Driver method i.e. java.main()
	
	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException
	{// main() is opened
		//create instant of Configuration object
		Configuration conf = new Configuration();
		
		//Passing command line arrguments by skipping Hadoop Generic options
		String myArgs[] = new GenericOptionsPasser(conf, args).getRemainingArgs();
		
		//Create the instance of job object
		Job myJob = new Job(conf, "Name of the job");
		
		/* We have to supply some details to job object like, main class, mapper class
		   reducer class, combiner class, partitioner class, Sorting class, DataType classes,
		   Input format / Output formats, Input/Output locations, Job Submission etc...)*/
		
		//Setting classes info
		myJob.setJarByClass(MRTemplate.class);
		myJob.setMapperClass(MyMapper.class);
		myJob.setReducerClass(MyReducer.class);
		
		//Setting the data types info
		myJob.setMapOutputKeyClass(MKEYOUT.class);
		myJob.setMapOutputValueClass(MVALUEOUT.class);
		myJob.setOutputKeyClass(RKEYOUT.class);
		myJob.setMapOutputValueClass(RVALUEOUT.class);
		
		//Setting the Input/Output format classes
		myJob.setInputFormatClass(MyInputFormat.class);
		myJob.setOutputFormatClass(MyOutputFormat.class);
		
		//Setting Input and Output file locations in File System i.e. HDFS...
		FileInputFormat<K, V>.addInputPath(myJob, new Path(myArg[0]));
		FileOutputFormat<K, V>.setOutputPath(myJob, new Path(myArg[1]));
		
		//Job submission and soft exit
		System.exit(myJob.waitForCompletion(true)?0:1);
	}//close the main method
}//close the main class
