git log --all --pretty=format:'commit_hash %H tree_hash %T parent_hashes %P author_email %ae author_date %ad committer_email %ce commit_subject %s' | sed -e 's/ /_/19g' | awk -v q='"' -v c=':' -v cl='{' -v cr='}' -v bl='[' -v br=']' -v e=',' -v t='    ' '
BEGIN {
	print cl;
	print t q "commits" q c bl;
}
{
	count++;
	if ( count > 1 ) {
		printf "%s\n", e
	}
	print t t cl;
	print t t t q "commit_nr" q c q count q e;
	print t t t q "commit_hash" q c q $2 q e;
	print t t t q "tree_hash" q c q $4 q e;
	if ( count == 1 ) {
		print t t t q "parent_hashes" q c q "$6" q e;
		print t t t q "author_email" q c q $8 q e;
		print t t t q "date_day_week" q c q $10 q e;
		print t t t q "date_month_name" q c q $11 q e;
		print t t t q "date_month_day" q c q $12 q e;
		print t t t q "date_hour" q c q $13 q e;
		print t t t q "date_year" q c q $14 q e;
		print t t t q "date_hour_gmt" q c q $15 q e;
		print t t t q "committer_email" q c q $17 q e;
		print t t t q "Commit_Subject" q c q $19 q e;
	} else {
		print t t t q "parent_hashes" q c q "$6" q e;
		print t t t q "author_email" q c q $8 q e;
		print t t t q "date_day_week" q c q $10 q e;
		print t t t q "date_month_name" q c q $11 q e;
		print t t t q "date_month_day" q c q $12 q e;
		print t t t q "date_hour" q c q $13 q e;
		print t t t q "date_year" q c q $14 q e;
		print t t t q "date_hour_gmt" q c q $15 q e;
		print t t t q "committer_email" q c q $17 q e;
		print t t t q "Commit_Subject" q c q $19 q e;
	}
	printf "%s%s%s", t, t, cr;
} END {
	printf "\n%s%s\n", t, br;
	print cr;
}' > gitlog.json