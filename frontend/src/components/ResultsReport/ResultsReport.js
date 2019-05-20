import React, { Component } from 'react';

class ResultsReport extends Component {
    constructor(props) {
        super(props);
        this.state = {
            resultsList: ["aefws", "aefrwgd"]
        }
    }
    render() {
        var resultsList = ""
        resultsList = this.state.resultsList.map((item) => {
            return (
                <div>{item}</div>
            )
        })
        return (
            <div className="container mt-5">
                <div className="formContainer">
                    <h1 style={{ color: "black" }}>Finalized Hackthons Results</h1>
                    <div>{resultsList}</div>
                </div>
            </div>
        );
    }
}

export default ResultsReport;