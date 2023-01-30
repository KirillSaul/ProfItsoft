import {TextField} from "@material-ui/core";
import React from 'react';

class CalculatorOutput extends React.Component {
    render() {
        return <TextField value={this.props.output}></TextField>
    }
}

export default CalculatorOutput;