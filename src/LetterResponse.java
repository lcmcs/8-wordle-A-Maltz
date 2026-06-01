public enum LetterResponse {
    CORRECT_LOCATION, // Green
    WRONG_LOCATION,   // Yellow
    WRONG_LETTER }    // Gray

    /*
    if users guesses "TRAIN" (and target is "SHLEP") response would be 5 WordleResponses,
    first would be a WordleReponse object with the following values
    c='T'
    index = 0
    LetterResponse = LetterResponse.WRONG_LETTER
     */