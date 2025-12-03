<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Self-Screening - EmotiCare</title>
    <link rel="stylesheet" href="<c:url value='/assets/css/emoticare.css'/>">
</head>
<body>
    <div class="container">
        <h1>Self-Screening Assessment</h1>
        <h2>${screeningType} Screening</h2>

        <form action="<c:url value='/screening/submit'/>" method="post">
            <input type="hidden" name="type" value="${screeningType}">

            <div class="screening-questions">
                <p><strong>Rate how often you have experienced the following over the past 2 weeks:</strong></p>
                <p>0 = Not at all, 1 = Several days, 2 = More than half the days, 3 = Nearly every day</p>

                <div class="question">
                    <label>1. Feeling down, depressed, or hopeless</label>
                    <select name="q1" required>
                        <option value="0">0 - Not at all</option>
                        <option value="1">1 - Several days</option>
                        <option value="2">2 - More than half the days</option>
                        <option value="3">3 - Nearly every day</option>
                    </select>
                </div>

                <div class="question">
                    <label>2. Little interest or pleasure in doing things</label>
                    <select name="q2" required>
                        <option value="0">0 - Not at all</option>
                        <option value="1">1 - Several days</option>
                        <option value="2">2 - More than half the days</option>
                        <option value="3">3 - Nearly every day</option>
                    </select>
                </div>

                <div class="question">
                    <label>3. Trouble falling or staying asleep</label>
                    <select name="q3" required>
                        <option value="0">0 - Not at all</option>
                        <option value="1">1 - Several days</option>
                        <option value="2">2 - More than half the days</option>
                        <option value="3">3 - Nearly every day</option>
                    </select>
                </div>

                <div class="question">
                    <label>4. Feeling tired or having little energy</label>
                    <select name="q4" required>
                        <option value="0">0 - Not at all</option>
                        <option value="1">1 - Several days</option>
                        <option value="2">2 - More than half the days</option>
                        <option value="3">3 - Nearly every day</option>
                    </select>
                </div>

                <div class="question">
                    <label>5. Feeling nervous, anxious, or on edge</label>
                    <select name="q5" required>
                        <option value="0">0 - Not at all</option>
                        <option value="1">1 - Several days</option>
                        <option value="2">2 - More than half the days</option>
                        <option value="3">3 - Nearly every day</option>
                    </select>
                </div>
            </div>

            <input type="hidden" name="score" id="totalScore" value="0">

            <button type="submit" onclick="calculateScore()">Submit Screening</button>
            <a href="<c:url value='/home'/>">Cancel</a>
        </form>
    </div>

    <script>
        function calculateScore() {
            const q1 = parseInt(document.getElementsByName('q1')[0].value);
            const q2 = parseInt(document.getElementsByName('q2')[0].value);
            const q3 = parseInt(document.getElementsByName('q3')[0].value);
            const q4 = parseInt(document.getElementsByName('q4')[0].value);
            const q5 = parseInt(document.getElementsByName('q5')[0].value);
            const total = q1 + q2 + q3 + q4 + q5;
            document.getElementById('totalScore').value = total;
        }
    </script>
</body>
</html>
