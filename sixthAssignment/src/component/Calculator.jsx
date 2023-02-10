import React from 'react';
import ButtonNumber from "./ButtonNumber";
import {Box, Grid} from "@material-ui/core";
import CalculatorOutput from "./CalculatorOutput";
import OutsideButton from "./OutsideButton";
import ButtonOperation from "./ButtonOperation";
import ButtonResult from "./ButtonResult";
import {connect} from "react-redux";
import {getExamples} from "../store/action/CalculatorAction";

function mapStateToProps(state) {
    return {
        examples: state.examples,
        isLoading: state.isLoading,
        isError: state.isError
    }
}

function mapDispatchToProps(dispatch) {
    return {dispatch}
}

class Calculator extends React.Component {
    constructor(props) {
        super(props);
        this.buttonNumberClick = this.buttonNumberClick.bind(this);
        this.buttonOperationClick = this.buttonOperationClick.bind(this);
        this.buttonResultClick = this.buttonResultClick.bind(this);
        this.state = {
            output: "0",
            firstNumber: "",
            secondNumber: "",
            operation: "",
            result: null,
            history: [],
        }
    }

    buttonNumberClick(buttonName) {
        const updateOutput = {output: this.state.output + buttonName}
        if (this.state.operation === "") {
            const updateFirstNumber = {firstNumber: this.state.firstNumber + buttonName};
            if (this.state.output === "0" && this.state.firstNumber === "") {
                this.refreshState({output: buttonName, ...updateFirstNumber})
            } else {
                this.refreshState({...updateOutput, ...updateFirstNumber})
            }
        } else {
            this.refreshState({...updateOutput, secondNumber: this.state.secondNumber + buttonName})
        }
    }

    buttonOperationClick(buttonName) {
        if (this.state.secondNumber !== "") {
            this.outResult(buttonName);
        } else if (this.state.operation !== "") {
            const newOutput = this.state.output.substring(0, this.state.output.length - 1) + buttonName
            this.refreshState({operation: buttonName, output: newOutput})
        } else {
            this.refreshState({operation: buttonName, output: this.state.output + buttonName})
        }
    }

    buttonResultClick() {
        if (this.state.secondNumber !== "") {
            this.outResult("=");
        }
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if (this.props.examples !== prevProps.examples) {
            this.resultLoaded();
        }
        if (this.props.isLoading !== prevProps.isLoading) {
            this.props.isLoading === true ? this.setState({output: "loading..."}) : this.setState({output: ""});
        }
        if (this.props.isError !== prevProps.isError) {
            this.props.isError === true ? this.setState({output: "Error"}) : this.setState({output: ""});
        }
    }

    async resultLoaded() {
        const examples = this.props.examples
        for (let example of examples) {
            for (let symbol of example) {
                if (symbol !== " ") {
                    if (symbol >= "0" && symbol <= "9") {
                        await this.buttonNumberClick(symbol)
                    } else {
                        await this.buttonOperationClick(symbol)
                    }
                }
            }
            await this.buttonResultClick()
            await this.refreshState({operation: "", firstNumber: "", output: ""})
        }
    }

    refreshState(stateToRefresh) {
        this.setState({...stateToRefresh})
    }

    count(operation) {
        switch (operation) {
            case "+":
                return Number(this.state.firstNumber) + Number(this.state.secondNumber);
            case "-":
                return Number(this.state.firstNumber) - Number(this.state.secondNumber);
            case "*":
                return Number(this.state.firstNumber) * Number(this.state.secondNumber);
            case "/":
                return Number(this.state.firstNumber) / Number(this.state.secondNumber);
            case "=":
                return this.count(this.state.operation);
        }
    }

    outError(error) {
        this.refreshState({
                firstNumber: "",
                secondNumber: "",
                result: "",
                output: error,
                operation: "",
                history: this.state.history.concat(this.state.output + "=" + error),
            }
        )
    }

    outResult(operation) {
        const result = operation === "=" ? this.count(operation) : this.count(this.state.operation);

        if (result === Infinity) {
            this.outError("Error division by zero")
        } else {
            const commonState = {
                firstNumber: result,
                secondNumber: "",
                history: this.state.history.concat(this.state.output + "=" + result),
                result: result
            }

            if (operation === "=") {
                this.refreshState({
                        output: result,
                        operation: "",
                        ...commonState
                    }
                )
            } else {
                this.refreshState({
                        output: result.toString() + operation,
                        operation: operation,
                        ...commonState
                    }
                )
            }
        }
    }

    render() {
        return <div>
            <div>
                {
                    this.state.history.map((value, index) => {
                            if (index === (this.state.history.length - 1)) {
                                return (
                                    <Grid key={index}>
                                        <Box fontWeight="fontWeightBold">
                                            {value}
                                        </Box>
                                    </Grid>
                                )
                            } else {
                                return <Grid key={index}>{value}</Grid>
                            }
                        }
                    )
                }
            </div>

            <Grid>
                <CalculatorOutput output={this.state.output}></CalculatorOutput>
            </Grid>


            <Grid>
                <ButtonOperation buttonName="+" onClick={this.buttonOperationClick}/>
                <ButtonOperation buttonName="-" onClick={this.buttonOperationClick}/>
                <ButtonOperation buttonName="*" onClick={this.buttonOperationClick}/>
                <ButtonOperation buttonName="/" onClick={this.buttonOperationClick}/>
                <ButtonResult buttonName="=" onClick={this.buttonResultClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="7" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="8" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="9" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="4" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="5" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="6" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="1" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="2" onClick={this.buttonNumberClick}/>
                <ButtonNumber buttonName="3" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <ButtonNumber buttonName="0" onClick={this.buttonNumberClick}/>
            </Grid>
            <Grid>
                <Box mt={3}>
                    <OutsideButton buttonName="Получить и решить примеры" onClick={()=> getExamples(this.props.dispatch)}/>
                </Box>
            </Grid>
        </div>
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Calculator);