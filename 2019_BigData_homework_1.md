# 1. define problem
Investors must collect a large amount of financial news daily as a reference for transaction analysis. Important news websites such as Bloomberg, Reuters, CNN, BBC ... the daily news volume adds up to more than 100, how can investors master The most immediate and important news is the main problem that this topic wants to solve. In the following, we use the news data of crude oil futures as an example to conduct a text exploration, from data collection, processing, analysis to the final establishment of a model, hoping to achieve the effect of automated financial news screening.
# 2. data
- text data: The crawler tries different historical sources of crude oil news and investigates more credible news sources. The amount of news in one year using the following three websites is approximately:
EBSCO Host has a total of 1308 articles, with an average of about 3500 words per article
OilPrice.com has 2218 articles, with an average of about 4,000 words per article
CNBC news total 907 articles, average about 2700 each
- Numerical data: WTI crude oil futures daily opening price, closing price, trading volume
# 3. workflow
- process text data
  - Pre-processing:
    1. whether to do stemming
        lemmatization is the reduction of a language vocabulary into any form
        Stemming is the stem or root form of the extracted word. lemmatization and stemming are two important ways of normalizing form of morphology. Both can achieve the purpose of effectively merging form of morphology.
    2. whether to do summarization
        If a single piece of news has too many words, it may become a noise and reduce the accuracy of prediction. Therefore, we try to use summarized and not summarized to observe whether the word count is one of the factors that make the model good or bad.
        Summarize method: Use the summarize function in the python gensim suite, each article is a 200-word summary.
  - Feature extraction:
    1. Word vector space for articles : 
        try Unigram, Bigram, Bigram with windows rolling
        use Tf-iDf to conduct vector space of each article
    2. Sentiment analysis: positive score, negative score
        In the calculation of sentiment, we use the following two methods:
        Sum of positive and negative words in a custom dictionary: The custom dictionary includes: Loughran and McDonald Sentiment Word Lists, which are defined for financial-related words, and a general positive and negative dictionary.
        Use SentimentIntensityAnalyzer from the nltk suite to calculate positive and negative
- define important news
For investors, what they pay most attention to is the price fluctuations of financial products. Therefore, when defining important news, we also mainly take whether that news will cause "significant fluctuations in futures prices".
    - Consider only volatility: Standard deviation is the main judgment basis
        1. Method 1: Standard deviation of opening price for the next n days> Threshold
        2. Method 2: Standard deviation of the opening price in the next n days ([+ 1, + n])> Standard deviation of opening price in the past m days ([-m, -1])
    - Consider volatility and ups and downs:
      1. Method 1: Set a Threshold, for example + 6%. When the increase in the next n days> Threshold, it is marked as 1, otherwise it is marked as 0.
      2. Method 2: In addition to meeting the Threshold, taking the sentiment analysis of the article as the into consideration
- Feature selection:
    - Mutual Information Gain
    - Chi-square
- modeling and predition
    Using the vector space created by the above dictionary and different important news methods. To be specific, using vector space above as X's to fit the y's, the pos or neg news defined by market price data.
    To classify news into pos and neg news, we can use the following models:
    SVM (Support Vector Machine)
    Naive bayse
    Random Forest
# 4. database
We use mongoDB as our data base, because of reasons below:
1. the data we are using is crawled from websites. MongoDB is good at storing website data and insterting data daily.
2. the relation between data are low 
3. For storage of objects and JSON data: Mongo's BSON data format is ideal for storing and querying in a documented format.