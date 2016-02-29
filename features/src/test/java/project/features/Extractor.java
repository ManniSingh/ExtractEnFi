package project.features;

import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Map;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

public class Extractor {
		
			
			private static final String CONSUMER_KEY		= "gYmSydmOJ2QNVH70YFDZOvqkg";
			private static final String CONSUMER_SECRET 	= "VRfMysHtXfHGq0kflZZGD62tOPA6dwvMlr5UOSuAoHXntiv8h8";
			//private static final int TWEETS_PER_QUERY		= 100;
			private static final int MAX_QUERIES			= 450;
			private static final String SEARCH_TERM			= "iphone4 review -RT";
			
			


			public static String cleanText(String text)
			{
				text = text.replace("\n", "\\n");
				text = text.replace("\t", "\\t");
				return text;
			}
	
			public static OAuth2Token getOAuth2Token()
			{
				OAuth2Token token = null;
				ConfigurationBuilder cb;

				cb = new ConfigurationBuilder();
				cb.setApplicationOnlyAuthEnabled(true);

				cb.setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET);

				try
				{
					token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();
				}
				catch (Exception e)
				{
					System.out.println("Could not get OAuth2 token");
					e.printStackTrace();
					System.exit(0);
				}

				return token;
			}
			
			public static Twitter getTwitter()
			{
				OAuth2Token token;

			
				token = getOAuth2Token();

				
				ConfigurationBuilder cb = new ConfigurationBuilder();

				cb.setApplicationOnlyAuthEnabled(true);

				cb.setOAuthConsumerKey(CONSUMER_KEY);
				cb.setOAuthConsumerSecret(CONSUMER_SECRET);

				cb.setOAuth2TokenType(token.getTokenType());
				cb.setOAuth2AccessToken(token.getAccessToken());

				return new TwitterFactory(cb.build()).getInstance();

			}
			
			
			public static void notmain(String[] args)
			
			{
				//Parser.getParse("My dog also likes eating sausage",lp);
				//Parser.getParse("My dog don't likes eating sausage",lp);
				//System.exit(0);
				
				int	totalTweets = 0;

				long maxID = -1;

				Twitter twitter = getTwitter();

				
				try
				{
					
					Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus("search");

					
					RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");


					
					System.out.printf("You have %d calls remaining out of %d, Limit resets in %d seconds\n",
									  searchTweetsRateLimit.getRemaining(),
									  searchTweetsRateLimit.getLimit(),
									  searchTweetsRateLimit.getSecondsUntilReset());


					
					for (int queryNumber=0;queryNumber < MAX_QUERIES; queryNumber++)
					{
						System.out.printf("\n\n!!! Starting loop %d\n\n", queryNumber);

						//	Do we need to delay because we've already hit our rate limits?
						if (searchTweetsRateLimit.getRemaining() == 0)
						{
							
							System.out.printf("!!! Sleeping for %d seconds due to rate limits\n", searchTweetsRateLimit.getSecondsUntilReset());
							Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset()+2) * 1000l);
						}

						Query q = new Query(SEARCH_TERM);			
						//q.setCount(TWEETS_PER_QUERY);				
						q.setLang("en");							

						//	If maxID is -1, then this is our first call and we do not want to tell Twitter what the maximum
						//	tweet id is we want to retrieve.  But if it is not -1, then it represents the lowest tweet ID
						//	we've seen, so we want to start at it-1 (if we start at maxID, we would see the lowest tweet
						//	a second time...
						if (maxID != -1)
						{
							q.setMaxId(maxID - 1);
						}

						
						QueryResult r = twitter.search(q);

						//	If there are NO tweets in the result set, it is Twitter's way of telling us that there are no
						//	more tweets to be retrieved.  Remember that Twitter's search index only contains about a week's
						//	worth of tweets, and uncommon search terms can run out of week before they run out of tweets
						if (r.getTweets().size() == 0)
						{
							break;			
						}


						//	loop through all the tweets and process them.  In this sample program, we just print them
						//	out, but in a real application you might save them to a database, a CSV file, do some
						//	analysis on them, whatever...
						for (Status s: r.getTweets())				// Loop through all the tweets...
						{
							//	Increment our count of tweets retrieved
							totalTweets++;

							//	Keep track of the lowest tweet ID.  If you do not do this, you cannot retrieve multiple
							//	blocks of tweets...
							if (maxID == -1 || s.getId() < maxID)
							{
								maxID = s.getId();
							}

							System.out.println(cleanText(s.getText()));

						}

						//	As part of what gets returned from Twitter when we make the search API call, we get an updated
						//	status on rate limits.  We save this now so at the top of the loop we can decide whether we need
						//	to sleep or not before making the next call.
						searchTweetsRateLimit = r.getRateLimitStatus();
					}

				}
				catch (Exception e)
				{
					//	Catch all -- you're going to read the stack trace and figure out what needs to be done to fix it
					System.out.println("That didn't work well...wonder why?");

					e.printStackTrace();

				}

				System.out.printf("\n\nA total of %d tweets retrieved\n", totalTweets);
				

		    }
		
		

		
	}


