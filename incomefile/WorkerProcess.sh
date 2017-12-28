ng_pid=0

get_pid(){
#output=`ps aux | grep Scheduler`
output=`ps aux | grep "WorkerShell"` 
set -- $output
pid=$2
npid=$4

echo "pid=$pid"
echo "npid=$npid"
  
if [[ -z "$npid" ]]
then  
     running_pid="no process"     
	 
else
     running_pid=$pid
     export running_pid
fi 
}
first_kill=1
check_pid(){
echo "running pid = $running_pid"
if [ "$running_pid" == "no process" ]
then
    echo "No process currently running"
	export first_kill=2
	#start_process
	#echo "Process Started Successfully"
else
	echo "process id = $running_pid"
	while true
	do 
	echo $first_kill
	if [ $first_kill == 1 ]
	   then
	   kill_pid
	   sleep 2
	   get_pid
	   check_pid
	else
	   echo "Process Killed Successfully"	   
       start_process	   
       echo "Process Restarted Successfully"	   
       break	
	fi   
	done
fi
}

kill_pid(){
   kill $running_pid
   echo "killing process id $running_pid"
}

start_process(){
	cd /var/www/html/telecom/incomefile/
    	mv /var/www/html/telecom/incomefile/	
	java -cp "asterisk-java.jar" Worker

}

get_pid
check_pid

