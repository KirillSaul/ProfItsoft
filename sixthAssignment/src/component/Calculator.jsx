import React from 'react';
import CalculatorButton from "./CalculatorButton";
import CalculatorOutput from "./CalculatorOutput";
import {Grid} from "@material-ui/core";

class Calculator extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            output: 0,
            firstNumber: null,
            secondNumber: null,
            operation: null,
            result: null,
            history: [],
        }
    }

    buttonClick = (buttonName) => {
        if (this.state.output === 0 && this.state.firstNumber === null) {
            this.setState({output: ""})
        }
        if ((buttonName === "=" && this.state.secondNumber !== null) || buttonName !== "=") {
            if (buttonName >= "0" && buttonName <= "9") {
                if (this.state.operation === null) {
                    this.setFirstNumber(buttonName)
                } else {
                    this.setSecondNumber(buttonName)
                }
                this.addOutputValue(buttonName)

            } else {
                this.setState({operation: buttonName});
                if (buttonName === "=" || this.state.secondNumber !== null) {
                    this.count();
                } else if (this.state.operation !== null) {
                    this.setState((state) => (
                        {
                            output: state.output.substring(0, state.output.length - 1) + buttonName
                        }
                    ));
                } else {
                    this.addOutputValue(buttonName)
                }
            }
        }
    }

    setFirstNumber(number) {
        this.setState((state) => ({firstNumber: Number(state.firstNumber) + number}));
    }

    setSecondNumber(number) {
        this.setState((state) => ({secondNumber: Number(state.secondNumber) + number}));
    }

    setResult(value) {
        this.setState({result: value});

    }

    addOutputValue(value) {
        this.setState((state) => ({output: state.output + value}));
    }

    addHistory() {
        this.setState((state) => (
            {
                history: this.state.history.concat(state.output + "=" + state.result)
            }
        ));
    }

    refreshState() {
        this.setState((state) => {
                const commonState = {
                    firstNumber: state.result,
                    secondNumber: null,
                    result: null
                }
                if (state.operation !== "=") {
                    return {
                        output: state.result + state.operation,
                        ...commonState
                    }
                } else {
                    return {
                        output: state.result,
                        operation: null,
                        ...commonState
                    }
                }
            }
        );
    }

    count() {
        switch (this.state.operation) {
            case "+":
                this.setResult(Number(this.state.firstNumber) + Number(this.state.secondNumber))
                break;
            case "-":
                this.setResult(Number(this.state.firstNumber) - Number(this.state.secondNumber))
                break;
            case "*":
                this.setResult(Number(this.state.firstNumber) * Number(this.state.secondNumber))
                break;
            case "/":
                this.setResult(Number(this.state.firstNumber) / Number(this.state.secondNumber))
                break;
        }
        this.addHistory();
        this.refreshState();
    }

    render() {
        return <div>
            {this.state.history.map((value, index) => <Grid key={index}>{value}</Grid>)}
            <Grid>
                <CalculatorOutput output={this.state.output}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="+" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="-" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="*" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="/" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="=" buttonClick={this.buttonClick}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="7" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="8" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="9" buttonClick={this.buttonClick}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="4" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="5" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="6" buttonClick={this.buttonClick}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="1" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="2" buttonClick={this.buttonClick}/>
                <CalculatorButton buttonName="3" buttonClick={this.buttonClick}/>
            </Grid>
            <Grid>
                <CalculatorButton buttonName="0" buttonClick={this.buttonClick}/>
            </Grid>
        </div>
    }
}

export default Calculator;