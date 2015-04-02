/**
 * http://wiki.apache.org/hadoop/WordCount
 * For the basic code structure, I referenced from the official website.
 */
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class hw32{

    public static double calcDist(double des, double est, double x2, double y2) {

        double square = 0;
        square = (des - x2) * (des - x2) + (est - y2) * (est - y2);
        double distance = 0;
        distance = Math.sqrt(square);
        return distance;
    }

    public static double matchcity(String city, double x1, double y1) {
        double distance = 0;

        //String string99="(null), 8377.600, 20828.500";

        String[] city1 ={"New York","Los Angeles","Chicago","Houston","Philadelphia","Phoenix","San Antonio","San Diego","Dallas","San Jose","Austin","Jacksonville","Indianapolis","San Francisco","Columbus","Fort Worth","Charlotte","Detroit","El Paso","Memphis","Boston","Seattle","Denver","Washington","Nashville","Baltimore","Louisville","Portland","Oklahoma City","Milwaukee","Las Vegas","Albuquerque","Tucson","Fresno","Sacramento","Long Beach","Kansas City","Mesa","Virginia Beach","Atlanta","Colorado Springs","Raleigh","Omaha","Miami","Oakland","Tulsa","Minneapolis","Cleveland","Wichita","Arlington","New Orleans","Bakersfield","Tampa","Honolulu","Anaheim","Aurora","Santa Ana","Riverside","Corpus Christi","Pittsburgh","Lexington","Anchorage","Stockton","Cincinnati","Saint Paul","Toledo","Newark","Greensboro","Plano","Henderson","Lincoln","Buffalo","Fort Wayne","Jersey City","Chula Vista","Orlando","St. Petersburg","Norfolk","Chandler","Laredo","Madison","Durham","Lubbock","Winston","Salem","Garland","Glendale","Hialeah","Reno","Baton Rouge","Irvine","Chesapeake","Irving","Scottsdale","Fremont","Gilbert","San Bernardino","Boise","Birmingham","(null)","test"};
        double[] city_x ={6967.400,17296.400,32293.800,26910.400,30959.500,18509.700,540.100,13136.200,5979.600,16064.400,168.300,13620.200,20587.600,21884.500,23898.600,12691.800,21903.200,28928.900,10883.400,26192.100,33366.300,5130.400,8482.100,7554.800,30408.400,36032.700,14144.900,10715.100,855.800,3338.500,2026.200,33116.600,9836.200,24912.800,26185.500,7401.900,16885.000,34211.100,3902.800,13885.300,21949.400,13024.000,8113.600,28938.800,35682.900,12455.300,23691.800,2290.200,18195.100,34226.500,32623.800,10923.000,2536.600,24624.600,31619.500,20979.200,6411.900,17130.300,18163.200,14619.000,20499.600,27243.700,17131.400,13257.200,1265.000,23896.400,3773.000,33210.100,12470.700,13515.700,11421.300,9799.900,10733.800,20446.800,27440.600,14333.000,32084.800,35850.100,1820.500,6994.900,22590.700,7131.300,4445.100,26785.000,33919.600,12122.000,26423.100,25518.900,26932.400,5207.400,2198.900,30731.800,4166.800,513.700,16382.300,24731.300,2663.100,7278.700,10465.400,8377.600,0};
        double[] city_y ={29150.000,12625.800,29658.200,6275.500,25609.100,10957.100,3294.500,5309.700,35630.100,4292.200,321.200,19163.100,21689.800,5991.700,16248.100,2055.900,28233.700,18738.500,31573.300,34454.200,19440.300,16655.100,31078.300,28101.700,35928.200,22040.700,9595.300,30281.900,13547.600,24409.000,316.800,9944.000,21190.400,30190.600,17479.000,26807.000,16506.600,26832.300,21591.900,26492.400,20631.600,5462.600,15324.100,18638.400,27088.600,6090.700,17729.800,25221.900,5316.300,5102.900,24974.400,15374.700,34840.300,5523.100,29616.400,6897.000,29454.700,5606.700,26384.600,10077.100,24590.500,26020.500,4434.100,30085.000,18635.100,15362.600,34217.700,19807.700,17002.700,30528.300,16439.500,35429.900,26643.100,7064.200,30256.600,18054.300,990.000,20638.200,19151.000,30386.400,23702.800,30354.500,3962.200,11320.100,10311.400,5055.600,30082.800,21634.800,9109.100,58.300,29059.800,7590.000,19939.700,4100.800,27112.800,19587.700,15741.000,25094.300,15739.900,20828.500,0};


        int i;
        for(i=0;i<=99;i++)
        {

            if (city.equals(city1[i])) {
                double ct0_1 = city_x[i];
                double ct0_2 = city_y[i];
                distance = calcDist(ct0_1,ct0_2,x1, y1);

                return distance;
            }

        }
        return 0;


    }

    public static class Map extends
            Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private final static IntWritable zero = new IntWritable(0);
        private Text word = new Text();

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            double distance = 0;
            String[] attributes = line.split(", ");

            double x1 = Double.parseDouble(attributes[2]);
            double y1 = Double.parseDouble(attributes[3]);
            String city = attributes[1];
            distance = matchcity(city, x1, y1);
            if (distance <= 5) {
                word.set(city);
                context.write(word, one);
            }
            else {
                word.set(city);
                context.write(word,zero);
            }

        }
    }

    public static class Reduce extends
            Reducer<Text, IntWritable, Text, IntWritable> {

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = new Job(conf, "hw32");
        job.setJarByClass(hw32.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
